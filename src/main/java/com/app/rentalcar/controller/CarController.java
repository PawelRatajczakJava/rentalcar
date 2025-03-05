package com.app.rentalcar.controller;

import com.app.rentalcar.model.Car;
import com.app.rentalcar.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @GetMapping
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @PostMapping
    public Car addCar(@RequestBody Car car) {
        return carRepository.save(car);
    }

    @PutMapping("/{id}")
    public Car updateCar(@PathVariable Long id, @RequestBody Car updatedCar) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found"));
        car.setMake(updatedCar.getMake());
        car.setModel(updatedCar.getModel());
        car.setYear(updatedCar.getYear());
        car.setAvailable(updatedCar.isAvailable());
        return carRepository.save(car);
    }

    @PatchMapping("/{id}")
    public Car patchCar(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        updates.forEach((key, value) -> {
            switch (key) {
                case "make":
                    car.setMake((String) value);
                    break;
                case "model":
                    car.setModel((String) value);
                    break;
                case "year":
                    if (value instanceof Number) {
                        car.setYear(((Number) value).intValue());
                    } else {
                        car.setYear(Integer.parseInt(value.toString()));
                    }
                    break;
                case "available":
                    if (value instanceof Boolean) {
                        car.setAvailable((Boolean) value);
                    } else {
                        car.setAvailable(Boolean.parseBoolean(value.toString()));
                    }
                    break;

            }
        });

        return carRepository.save(car);
    }

    @DeleteMapping("/{id}")
    public String deleteCar(@PathVariable Long id) {
        carRepository.deleteById(id);
        return "Car deleted";
    }
}
