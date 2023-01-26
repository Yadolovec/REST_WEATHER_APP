package com.app.Weather.utils;

import com.app.Weather.DTO.SensorDTO;
import com.app.Weather.models.Sensor;
import com.app.Weather.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return SensorDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDTO sensorDTO = (SensorDTO) target;
        if (sensorService.findByName(sensorDTO.getSensorName())!=null){
            errors.rejectValue("sensorName", "", "This name is already taken");
        }
    }
}
