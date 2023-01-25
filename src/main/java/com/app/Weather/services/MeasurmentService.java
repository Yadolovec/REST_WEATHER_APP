package com.app.Weather.services;

import com.app.Weather.models.Measurment;
import com.app.Weather.repositories.MeasurmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurmentService {
    private final MeasurmentRepository measurmentRepository;
    @Autowired
    public MeasurmentService(MeasurmentRepository measurmentRepository) {
        this.measurmentRepository = measurmentRepository;
    }
    public Measurment findOne(int id){
        return measurmentRepository.findById(id).orElse(null);
    }
    public List<Measurment> findAll(){
        return measurmentRepository.findAll();
    }
}
