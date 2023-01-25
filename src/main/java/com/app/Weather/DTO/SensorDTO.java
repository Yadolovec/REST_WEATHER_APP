package com.app.Weather.DTO;

import jakarta.persistence.Column;

public class SensorDTO {
    @Column(name = "sensor_name")
    private String sensorName;


    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }
}
