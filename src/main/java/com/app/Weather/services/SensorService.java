package com.app.Weather.services;

import com.app.Weather.models.Sensor;
import com.app.Weather.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorService {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public Sensor findOne(int id){
        return sensorRepository.findById(id).orElse(null);
    }

    public List<Sensor> findAll(){
        return sensorRepository.findAll();
    }

    public Sensor findByName(String sensorName){
        return sensorRepository.findSensorBySensorName(sensorName).orElse(null);
    }

}
