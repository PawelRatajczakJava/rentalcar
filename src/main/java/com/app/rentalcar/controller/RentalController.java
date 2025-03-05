package com.app.rentalcar.controller;

import com.app.rentalcar.model.Rental;
import com.app.rentalcar.model.Car;
import com.app.rentalcar.model.User;
import com.app.rentalcar.repository.RentalRepository;
import com.app.rentalcar.repository.CarRepository;
import com.app.rentalcar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    @PostMapping("/{carId}/{username}")
    public String rentCar(@PathVariable Long carId, @PathVariable String username) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));
        if (!car.isAvailable()) {
            return "Car is not available";
        }
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Rental rental = new Rental();
        rental.setCar(car);
        rental.setUser(user);
        rental.setRentalDate(LocalDate.now());
        rentalRepository.save(rental);
        car.setAvailable(false);
        carRepository.save(car);
        return "Car rented successfully";
    }
}
