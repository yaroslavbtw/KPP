package com.example.restservice.service;
import com.example.restservice.responses.StatisticResponse;
import com.example.restservice.logger.MyLogger;

import java.util.*;

public class Statistic {

    List<List<Long>> inputList = new ArrayList<>();
    List<Long> resultList = new ArrayList<>();

    public Statistic() {
    }

    public void addInputList(List<List<Long>> List) {
        this.inputList.addAll(List);
    }

    public void addResultList(List<Long> List) {
        this.resultList.addAll(List);
    }

    public OptionalLong findMaxResult() {
        MyLogger.info("Result list for max: " + resultList);
        return resultList
                .stream()
                .mapToLong(Long::longValue)
                .max();
    }

    public OptionalLong findMinResult() {
        MyLogger.info("Result list for min: " + resultList);
        return resultList
                .stream()
                .mapToLong(Long::longValue)
                .min();
    }

    public StatisticResponse getStat()
    {
        long countOfIncorrect = 0;
        for(List<Long> param : inputList)
            if(param.size() == 0 || param.size() % 2 != 0) countOfIncorrect++;
        HashMap<Long, Integer> allRes = new HashMap<>();
        ArrayList<Long> res = new ArrayList<>(resultList);
        Long common = 0L;
        int max = 0;
        for(int x = 0; x < res.size(); x++)
        {
            int count = 1;
            if(allRes.containsKey(res.get(x)))
                continue;
            for(int y = 0; y < res.size(); y++)
            {
                if(x != y)
                {
                    if(Objects.equals(res.get(x), res.get(y)))
                        count++;
                }
            }
            allRes.put(res.get(x),count);
            if(count > max){
                max = count;
                common = res.get(x);
            }
        }
        return new StatisticResponse(inputList.size(), countOfIncorrect, this.findMaxResult(), this.findMinResult(), common);
    }


}
