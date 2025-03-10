package com.app.rentalcar.controller;

import com.app.rentalcar.dto.RentalRequestDTO;
import com.app.rentalcar.dto.RentalResponseDTO;
import com.app.rentalcar.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @PostMapping("/rent/{carId}")
    public ResponseEntity<RentalResponseDTO> rentCar(@PathVariable Long carId, @RequestParam String username) {
        RentalResponseDTO rentalResponse = rentalService.rentCar(carId, username);
        return ResponseEntity.status(HttpStatus.CREATED).body(rentalResponse);
    }

    @PostMapping("/return/{rentalId}")
    public ResponseEntity<RentalResponseDTO> returnCar(@PathVariable Long rentalId) {
        RentalResponseDTO rentalResponseDTO = rentalService.returnCar(rentalId);
        return ResponseEntity.ok(rentalResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<RentalResponseDTO>> getAllRentals() {
        List<RentalResponseDTO> rentals = rentalService.getAllRentals();
        return ResponseEntity.ok(rentals);
    }

    @GetMapping("/user")
    public ResponseEntity<List<RentalResponseDTO>> getUserRentals(@PathVariable String username) {
        List<RentalResponseDTO> rentals = rentalService.getRentalsByUser(username);
        return ResponseEntity.ok(rentals);
    }
}
