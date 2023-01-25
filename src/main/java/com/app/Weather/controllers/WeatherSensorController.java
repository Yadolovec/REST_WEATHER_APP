package com.app.Weather.controllers;

import com.app.Weather.DTO.MeasurmentDTO;
import com.app.Weather.services.MeasurmentService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherSensorController {
    private final MeasurmentService measurmentService;
    private final ModelMapper modelMapper;


    public WeatherSensorController(MeasurmentService measurmentService, ModelMapper modelMapper) {
        this.measurmentService = measurmentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/measurments")
    public MeasurmentDTO measurments(){
        return modelMapper.map(measurmentService.findOne(4), MeasurmentDTO.class);
    }
}
