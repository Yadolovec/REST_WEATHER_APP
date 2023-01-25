package com.app.Weather.DTO;

import com.app.Weather.models.Sensor;
import com.app.Weather.services.SensorService;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class MeasurmentDTO {

    @Column(name = "value_temp")
    private double value; // -100 to 100
    @Column(name = "raining")
    private boolean raining; // NOT EMPTY
    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    private Sensor sensor; // NOT EMPTY and PRESENT

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

    public String getSensor() {
        return sensor.getSensorName();
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
