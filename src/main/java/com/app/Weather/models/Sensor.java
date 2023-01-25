package com.app.Weather.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "sensor")
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "sensor_name")
    private String sensorName; //UNIQUE and FROM 3 TO 30 SYLLABLES
    @OneToMany(mappedBy = "sensor")
    private List<Measurement> measurements;

    public Sensor() {
    }

    public Sensor(String sensorName, List<Measurement> measurements) {
        this.sensorName = sensorName;
        this.measurements = measurements;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSensorName() {
        return sensorName;
    }



    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public List<Measurement> getMeasurments() {
        return measurements;
    }

    public void setMeasurments(List<Measurement> measurements) {
        this.measurements = measurements;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "id=" + id +
                ", sensorName='" + sensorName + '\'' +
                '}';
    }
}
