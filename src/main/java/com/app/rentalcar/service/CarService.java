package com.app.rentalcar.service;

import com.app.rentalcar.dto.CarRequestDTO;
import com.app.rentalcar.dto.CarResponseDTO;
import com.app.rentalcar.entity.Car;

import java.util.List;

public interface CarService {
    List<CarResponseDTO> getAllCars();
    CarResponseDTO addCar(CarRequestDTO carRequestDTO);
    CarResponseDTO updateCar(Long id, CarRequestDTO carRequestDTO);
    void deleteCar(Long id);
}