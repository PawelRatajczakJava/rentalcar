package com.app.rentalcar.repository;

import com.app.rentalcar.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
