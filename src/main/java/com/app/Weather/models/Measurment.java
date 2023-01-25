package com.app.Weather.models;

import jakarta.persistence.*;

@Entity
@Table(name="measurment")
public class Measurment {
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

    public Measurment() {
    }

    public Measurment(double value, boolean raining, Sensor sensor) {
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

    @Override
    public String toString() {
        return "Measurment{" +
                "\nid=" + id +
                "\nvalue=" + value +
                "\nraining=" + raining +
                "\nsensor=" + sensor.getSensorName() +
                "\n"+ '}';
    }
}
