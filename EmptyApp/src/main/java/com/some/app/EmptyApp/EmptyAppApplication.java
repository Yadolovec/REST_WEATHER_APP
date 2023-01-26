package com.some.app.EmptyApp;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.some.app.EmptyApp.DTO.MeasurementDTO;
import com.some.app.EmptyApp.DTO.ResponseMeasurements;
import com.some.app.EmptyApp.DTO.SensorDTO;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

public class EmptyAppApplication {

	public static void main(String[] args) throws JsonProcessingException {
		SensorDTO sensorDTO = new SensorDTO();
		sensorDTO.setSensorName("SENSOR1");
		RestTemplate restTemplate = new RestTemplate();


		MeasurementDTO measurementDTO = new MeasurementDTO();
		measurementDTO.setSensorDTO(sensorDTO);
		measurementDTO.setValue(77);
		measurementDTO.setRaining(true);

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//		String s = ow.writeValueAsString(measurementDTO);
//		System.out.println(s);

		ObjectMapper om = new ObjectMapper();
		Map map;
		HttpEntity<Map> request;
		String url = "http://localhost:8080/measurements/add";;
		String ss;
//		for (int i = 0; i<100; i++){
//			map = om.convertValue(makeRandomMeasure(sensorDTO), Map.class);
//			request = new HttpEntity<>(map);
//			ss = restTemplate.postForObject(url, request, String.class);
//		}

//		double[] xData = new double[] { 0.0, 1.0, 2.0 };
//		double[] yData = new double[] { 2.0, 1.0, 0.0 };
//		// Create Chart
//		XYChart chart = QuickChart.getChart("Sample Chart", "X", "Y", "y(x)", xData, yData);
//		// Show it
//		new SwingWrapper(chart).displayChart();

		url = "http://localhost:8080/measurements";
		MeasurementDTO[] measurements = restTemplate.getForObject(url, MeasurementDTO[].class);

		List<Double> temps = new ArrayList<>();

		for (MeasurementDTO m : measurements){
			temps.add(m.getValue());
		}

		double[] xData = IntStream.range(0, measurements.length).asDoubleStream().toArray();
		double[] yData = temps.stream().mapToDouble(x -> x).toArray();
		XYChart chart = QuickChart.getChart("Sample Chart", "X", "Y", "y(x)", xData, yData);
		new SwingWrapper(chart).displayChart();
	}
	public static MeasurementDTO makeRandomMeasure(SensorDTO sensorDTO){
		MeasurementDTO measurementDTO = new MeasurementDTO();
		measurementDTO.setSensorDTO(sensorDTO);
		Random random = new Random();
		measurementDTO.setValue(-100.0 + 200.0*random.nextDouble());
		measurementDTO.setRaining(random.nextBoolean());

		return measurementDTO;
	}

}
