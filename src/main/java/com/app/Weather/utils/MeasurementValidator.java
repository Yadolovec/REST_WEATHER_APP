package com.app.Weather.utils;

import com.app.Weather.DTO.MeasurementDTO;
import com.app.Weather.DTO.SensorDTO;
import com.app.Weather.services.SensorService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MeasurementValidator implements Validator {

    private final SensorService sensorService;

    public MeasurementValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return MeasurementDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasurementDTO measurementDTO = (MeasurementDTO) target;
        if (sensorService.findByName(measurementDTO.getSensorDTO().getSensorName())==null){
            errors.rejectValue("sensorDTO", "", "There is no sensor with that name" );
        }

        if (measurementDTO.getValue()>100||measurementDTO.getValue()<-100){
            errors.rejectValue("value", "", "Temperature should be between -100 to 100" );
        }

        if (measurementDTO.isRaining()==null){
            errors.rejectValue("raining", "", "Raining couldnt be empty" );
        }

    }
}
