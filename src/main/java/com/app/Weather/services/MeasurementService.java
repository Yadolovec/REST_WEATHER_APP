package com.app.Weather.services;

import com.app.Weather.models.Measurement;
import com.app.Weather.repositories.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }
    public Measurement findOne(int id){
        return measurementRepository.findById(id).orElse(null);
    }
    public List<Measurement> findAll(){
        return measurementRepository.findAll();
    }

    public List<Measurement> findAllWhenRaining(){
        return measurementRepository.findMeasurementByRainingTrue();
    }

}
