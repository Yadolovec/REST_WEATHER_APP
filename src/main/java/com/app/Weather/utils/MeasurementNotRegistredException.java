package com.app.Weather.utils;

public class MeasurementNotRegistredException extends RuntimeException{
    public MeasurementNotRegistredException(String msg){
        super(msg);
    }
}
