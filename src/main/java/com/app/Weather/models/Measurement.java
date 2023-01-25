package com.app.Weather.models;

import com.app.Weather.DTO.SensorDTO;
import jakarta.persistence.*;
import org.modelmapper.ModelMapper;

@Entity
@Table(name="measurment")
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "value_temp")
    private double value; // -100 to 100
    @Column(name = "raining")
    private boolean raining; // NOT EMPTY
    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    private Sensor sensor; // NOT EMPTY and PRESENT

    @Transient
    private SensorDTO sensorDTO;


    public Measurement() {
    }

    public Measurement(double value, boolean raining, Sensor sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public SensorDTO getSensorDTO() {
        ModelMapper modelMapper = new ModelMapper();
        return  modelMapper.map(sensor, SensorDTO.class);
    }

    public void setSensorDTO(SensorDTO sensorDTO) {
        this.sensorDTO = sensorDTO;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "\nid=" + id +
                "\nvalue=" + value +
                "\nraining=" + raining +
                "\nsensor=" + sensor.getSensorName() +
                "\n"+ '}';
    }
}
