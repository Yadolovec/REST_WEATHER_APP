package com.app.Weather.controllers;

import com.app.Weather.DTO.MeasurementDTO;
import com.app.Weather.DTO.SensorDTO;
import com.app.Weather.models.Measurement;
import com.app.Weather.models.Sensor;
import com.app.Weather.services.MeasurementService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherSensorController {
    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;


    public WeatherSensorController(MeasurementService measurementService, ModelMapper modelMapper) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/measurments")
    public MeasurementDTO measurments(){
        return convertMeasureToDTO(measurementService.findOne(4));
    }

    public SensorDTO convertSensorToDTO(Sensor sensor){
        return modelMapper.map(sensor, SensorDTO.class);
    }
    public MeasurementDTO convertMeasureToDTO(Measurement measurement){
        return modelMapper.map(measurement, MeasurementDTO.class);
    }
}
