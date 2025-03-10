package com.app.rentalcar.service;

import com.app.rentalcar.dto.RentalRequestDTO;
import com.app.rentalcar.dto.RentalResponseDTO;

import java.util.List;

public interface RentalService {
    RentalResponseDTO rentCar(Long carId, String renterUsername);
    RentalResponseDTO returnCar(Long rentalId);
    List<RentalResponseDTO> getAllRentals();
    List<RentalResponseDTO> getRentalsByUser(String username);
}
