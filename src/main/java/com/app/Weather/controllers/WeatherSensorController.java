package com.app.Weather.controllers;

import com.app.Weather.DTO.MeasurementDTO;
import com.app.Weather.DTO.SensorDTO;
import com.app.Weather.models.Measurement;
import com.app.Weather.models.Sensor;
import com.app.Weather.services.MeasurementService;
import com.app.Weather.services.SensorService;
import com.app.Weather.utils.*;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class WeatherSensorController {
    private final MeasurementService measurementService;
    private final SensorService sensorService;
    private final ModelMapper modelMapper;
    private final SensorValidator sensorValidator;
    private final MeasurementValidator measurementValidator;


    public WeatherSensorController(MeasurementService measurementService, SensorService sensorService, ModelMapper modelMapper, SensorValidator sensorValidator, MeasurementValidator measurementValidator) {
        this.measurementService = measurementService;
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
        this.sensorValidator = sensorValidator;
        this.measurementValidator = measurementValidator;
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
    public ResponseEntity<HttpStatus> register(@RequestBody @Valid SensorDTO sensorDTO,
                                               BindingResult bindingResult){

        sensorValidator.validate(sensorDTO, bindingResult);

        if (bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();
            List <FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors){
                errorMsg
                        .append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append("; ");
            }

            throw new SensorNotCreatedException(errorMsg.toString());
        }

        Sensor sensor = new Sensor();
        sensor.setSensorName(sensorDTO.getSensorName());

        sensorService.save(sensor);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/measurements/add")
    public ResponseEntity<HttpStatus> addMeasure(@RequestBody @Valid MeasurementDTO measurementDTO,
                                                 BindingResult bindingResult){

        measurementValidator.validate(measurementDTO, bindingResult);

        if(bindingResult.hasErrors()){
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error : errors){
                errorMessage
                        .append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append("; ");
            }

            throw new MeasurementNotRegistredException(errorMessage.toString());
        }

        Measurement measurement = new Measurement();
        measurement = convertMeasureFromDTO(measurementDTO);
        measurement.setSensor(
                sensorService.findByName(measurement.getSensorDTO().getSensorName())
        );
        measurement.setCreatedTime(LocalDateTime.now());
        measurementService.save(measurement);
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

    public Measurement convertMeasureFromDTO(MeasurementDTO measurementDTO){
        return modelMapper.map(measurementDTO, Measurement.class);
    }


    @ExceptionHandler
    public ResponseEntity<ExceprionHandler> errorHandler(SensorNotCreatedException e){
        ExceprionHandler handler = new ExceprionHandler(
                e.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(handler, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ExceprionHandler> errorHandler(MeasurementNotRegistredException e){
        ExceprionHandler handler = new ExceprionHandler(
                e.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(handler, HttpStatus.BAD_REQUEST);
    }

}
