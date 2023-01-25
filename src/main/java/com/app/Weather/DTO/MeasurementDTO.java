package com.app.Weather.DTO;

import jakarta.persistence.Column;

public class MeasurementDTO {

    @Column(name = "value_temp")
    private double value; // -100 to 100
    @Column(name = "raining")
    private boolean raining; // NOT EMPTY
    private SensorDTO sensorDTO;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensorDTO() {
        return sensorDTO;
    }

    public void setSensorDTO(SensorDTO sensorDTO) {
        this.sensorDTO = sensorDTO;
    }
}
