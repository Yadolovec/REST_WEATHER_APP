package com.app.Weather.controllers;

import com.app.Weather.DTO.MeasurementDTO;
import com.app.Weather.DTO.SensorDTO;
import com.app.Weather.models.Measurement;
import com.app.Weather.models.Sensor;
import com.app.Weather.services.MeasurementService;
import com.app.Weather.services.SensorService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class WeatherSensorController {
    private final MeasurementService measurementService;
    private final SensorService sensorService;
    private final ModelMapper modelMapper;


    public WeatherSensorController(MeasurementService measurementService, SensorService sensorService, ModelMapper modelMapper) {
        this.measurementService = measurementService;
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/measurements")
    public List<MeasurementDTO> measurments(){
        return measurementService.findAll().stream().map(this::convertMeasureToDTO).collect(Collectors.toList());
    }

    @GetMapping("/measurements/rainyDaysCount")
    public List<MeasurementDTO> measurmentsInRain(){
        return measurementService.findAllWhenRaining().stream().map(this::convertMeasureToDTO).collect(Collectors.toList());
    }

    @PostMapping("/sensors/registration")
    public ResponseEntity<HttpStatus> register(@RequestBody SensorDTO sensorDTO){
        Sensor sensor = new Sensor();
        sensor.setSensorName(sensorDTO.getSensorName());
        sensorService.save(sensor);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    public SensorDTO convertSensorToDTO(Sensor sensor){
        return modelMapper.map(sensor, SensorDTO.class);
    }
    public Sensor convertSensorFromDTO(SensorDTO sensorDTO){
        return sensorService.findByName(sensorDTO.getSensorName());
    }

    public MeasurementDTO convertMeasureToDTO(Measurement measurement){
        return modelMapper.map(measurement, MeasurementDTO.class);
    }
}
