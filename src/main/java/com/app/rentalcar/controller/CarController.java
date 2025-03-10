package com.app.rentalcar.controller;

import com.app.rentalcar.dto.CarRequestDTO;
import com.app.rentalcar.dto.CarResponseDTO;
import com.app.rentalcar.service.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/cars")
@Validated
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public ResponseEntity<List<CarResponseDTO>> getAllCars() {
        List<CarResponseDTO> cars = carService.getAllCars();
        return ResponseEntity.ok(cars);
    }

    @PostMapping
    public ResponseEntity<CarResponseDTO> addCar(@Valid @RequestBody CarRequestDTO carRequestDTO) {
        CarResponseDTO savedCar = carService.addCar(carRequestDTO);
        return ResponseEntity.ok(savedCar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarResponseDTO> updateCar(@PathVariable Long id, @Valid @RequestBody CarRequestDTO carRequestDTO) {
        CarResponseDTO updatedCar = carService.updateCar(id, carRequestDTO);
        return ResponseEntity.ok(updatedCar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.ok("Car deleted");
    }
}
