package com.app.Weather.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;


public class MeasurementDTO {

    @Column(name = "value_temp")
    @Range(min = -100, max = 100, message = "Temperature should be between -100 to 100")
//    @Min(10)
    private double value; // -100 to 100
    @Column(name = "raining")
//    @NotEmpty(message = "Raining should not be empty")
    @NotNull(message = "should not be null")
    private Boolean raining; // NOT EMPTY
    private SensorDTO sensorDTO;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Boolean isRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public Boolean getRaining() {return raining; }

    public SensorDTO getSensorDTO() {
        return sensorDTO;
    }

    public void setSensorDTO(SensorDTO sensorDTO) {
        this.sensorDTO = sensorDTO;
    }
}
