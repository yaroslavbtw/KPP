package com.example.restservice.service;

import com.example.restservice.logger.MyLogger;

import java.util.List;


public class GeometricFigure{

    private final long length;
    private final long width;

    public GeometricFigure(long len, long wid) {
        this.length = len;
        this.width = wid;
    }
    public GeometricFigure() {
        this.length = 0;
        this.width = 0;
    }

    public List<Long> calculateBulkSquare(List<List<Long>> bulk)
    {
        return bulk.stream().map(x->x.get(0)* x.get(1)).toList();
    }

    public List<Long> calculateBulkSquare(List<List<Long>> bulk, Statistic statist)
    {
        statist.addInputList(bulk);
        List<Long> res = bulk.stream().map(x->x.get(0)* x.get(1)).toList();
        statist.addResultList(res);
        return res;
    }

    public List<Long> calculateBulkPerimeter(List<List<Long>> bulk)
    {
        return bulk.stream().map(x->(x.get(0) + x.get(1)) * 2).toList();
    }

    public List<Long> calculateBulkPerimeter(List<List<Long>> bulk,  Statistic statist)
    {
        statist.addInputList(bulk);
        List<Long> res = bulk.stream().map(x->(x.get(0) + x.get(1)) * 2).toList();
        statist.addResultList(res);
        return res;
    }
    public long calculatePerimeter() {
        long perimeterOfObj;
        if(this.length == 0 || this.width == 0) {
            return 0;
        }
        else perimeterOfObj = 2 * (this.length + this.width);
        MyLogger.info("perimeter calculated");
        return perimeterOfObj;
    }

    public long calculateSquare()
    {
        long squareOfObject;
        squareOfObject = this.length * this.width;
        MyLogger.info("square calculated");
        return squareOfObject;
    }

    public long getLength() {
        return this.length;
    }

    public long getWidth() {
        return this.width;
    }

}
