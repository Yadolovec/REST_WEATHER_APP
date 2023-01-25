package com.app.Weather.repositories;

import com.app.Weather.models.Measurment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurmentRepository extends JpaRepository<Measurment, Integer> {
}
