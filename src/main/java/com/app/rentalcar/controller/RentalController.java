package com.app.rentalcar.controller;

import com.app.rentalcar.model.Rental;
import com.app.rentalcar.model.Car;
import com.app.rentalcar.model.User;
import com.app.rentalcar.repository.RentalRepository;
import com.app.rentalcar.repository.CarRepository;
import com.app.rentalcar.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    @Transactional
    @PreAuthorize("isAuthenticated()")
    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    @PostMapping("/{carId}")
    @Transactional
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> rentCar(@PathVariable Long carId) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found"));
        if (!car.isAvailable()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Car is not available");
        }
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Rental rental = new Rental();
        rental.setCar(car);
        rental.setUser(user);
        rental.setRentalDate(LocalDate.now());
        rentalRepository.save(rental);
        car.setAvailable(false);
        carRepository.save(car);
        return ResponseEntity.ok("Car rented successfully");
    }
}
