package com.some.app.EmptyApp.DTO;

import java.util.List;

public class ResponseMeasurements {
    List<MeasurementDTO> measurements;

    public List<MeasurementDTO> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<MeasurementDTO> measurements) {
        this.measurements = measurements;
    }
}
