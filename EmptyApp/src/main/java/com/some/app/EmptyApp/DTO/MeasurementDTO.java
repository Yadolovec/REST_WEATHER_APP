package com.some.app.EmptyApp.DTO;




public class MeasurementDTO {

//    @Min(10)
    private double value; // -100 to 100
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
