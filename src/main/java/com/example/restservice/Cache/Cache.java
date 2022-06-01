package com.example.restservice.Cache;

import com.example.restservice.logger.MyLogger;
import com.example.restservice.service.GeometricFigure;
import java.util.HashMap;

public class Cache {
    public static HashMap<String, Long> mapPerimeter = new HashMap<>();
    public static HashMap<String, Long> mapSquare = new HashMap<>();

    public void putPerimeter(GeometricFigure obj, long perimeter) {
        String numbers = obj.getLength() + " " + obj.getWidth();
        if (!mapPerimeter.containsKey(numbers)) {
            mapPerimeter.put(numbers,perimeter);
            MyLogger.info("Key: '" + numbers + "' Value: " + perimeter +  " is put in cache");
        }
        else {
            MyLogger.info("Responce is already in cache!");
        }
    }

    public void putSquare(GeometricFigure obj, long square) {
        String numbers = obj.getLength() + " " + obj.getWidth();
        if (!mapSquare.containsKey(numbers)) {
            mapSquare.put(numbers,square);
            MyLogger.info("Key: '" + numbers + "' Value: " + square +  " is put in cache");
        }
        else {
            MyLogger.info("Responce is already in cache!");
        }
    }

    public long getSquare(GeometricFigure obj) {
        String numbers = obj.getLength() + " " + obj.getWidth();
        long results = mapSquare.get(numbers);
        MyLogger.info("Value: " + results + " is got from cache with Key: " + numbers);
        return results;
    }

    public long getPerimeter(GeometricFigure obj) {
        String numbers = obj.getLength() + " " + obj.getWidth();
        long results = mapPerimeter.get(numbers);
        MyLogger.info("Value: " + results + " is got from cache with Key: " + numbers);
        return results;
    }

    public boolean containsKeyPerimeter(GeometricFigure key){
        String numbers = key.getLength() + " " + key.getWidth();
        return mapPerimeter.containsKey(numbers);
    }

    public boolean containsKeySquare(GeometricFigure key){
        String numbers = key.getLength() + " " + key.getWidth();
        return mapSquare.containsKey(numbers);
    }

    public void printMapPerimeter(){
        System.out.println("Cache:"+mapPerimeter);
    }

    public void printMapSquare(){
        System.out.println("Cache:"+mapSquare);
    }
}
