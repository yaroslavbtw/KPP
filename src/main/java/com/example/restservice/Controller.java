package com.example.restservice;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.concurrent.Semaphore;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class Controller {
    public static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    Cache cache = context.getBean("cache", Cache.class);
    private static Statistic stat = new Statistic();
//    public static List <List<Long>> inputList = new ArrayList<>();
//    public static List<Long> resultList = new ArrayList<>();

    @GetMapping("/square")
    public long squareReturn(@RequestParam(value = "length", defaultValue = "0") long length,
                               @RequestParam(value = "width", defaultValue = "0") long width) throws ServiceException{

        this.checkParams(length,width);
        GeometricFigure obj = new GeometricFigure(length,width);
        long square;
        cache.printMapSquare();
        Semaphore semaphore = new Semaphore(1, true);
        if(cache.containsKeySquare(obj)) {
            square = cache.getSquare(obj);
            MyLogger.info("Result already in cache: " + square);

        }
        else {
            cache.putSquare(obj, obj.calculateSquare());
            MyLogger.info("Result calculated and added in cache");
            square = obj.calculateSquare();
        }
        Counter.inc();
        return square;
    }

    @GetMapping("/perimeter")
    public long perimeterReturn(@RequestParam(value = "length", defaultValue = "0") long length,
                               @RequestParam(value = "width", defaultValue = "0") long width) throws ServiceException{
        this.checkParams(length,width);
        GeometricFigure obj = new GeometricFigure(length,width);
        long perimeter;
        cache.printMapPerimeter();
        if(cache.containsKeyPerimeter(obj)) {
            perimeter = cache.getPerimeter(obj);
            MyLogger.info("Result already in cache: " + perimeter);

        }
        else {
            cache.putPerimeter(obj, obj.calculatePerimeter());
            MyLogger.info("Result calculated and added in cache");
            perimeter = obj.calculatePerimeter();
        }
        Counter.inc();
        return perimeter;
    }

    @PostMapping("/square_bulk")
    public List<Long> bulkSquare(@RequestBody List<List<Long>> params){
        GeometricFigure bulk = new GeometricFigure();
        Counter.inc();
        return bulk.calculateBulkSquare(params,stat);
    }

    @PostMapping("/perimeter_bulk")
    public List<Long> bulkPerimeter(@RequestBody List<List<Long>> params){
        GeometricFigure bulk = new GeometricFigure();
        Counter.inc();
        return bulk.calculateBulkPerimeter(params, stat);
    }

    @PostMapping("/last_stat")
    public StatisticResponse lastStatistics() {
        return stat.getStat();
    }

    private void checkParams(long len, long wid)
    {
        if(len<=0){
            throw new ServiceException("illegal argument : length <=0 ");
        }
        if(wid<=0){
            throw new ServiceException("illegal argument : width <=0 ");
        }
    }
}
