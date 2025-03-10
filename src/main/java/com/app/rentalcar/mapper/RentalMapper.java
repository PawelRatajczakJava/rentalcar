package com.app.rentalcar.mapper;

import com.app.rentalcar.dto.RentalRequestDTO;
import com.app.rentalcar.dto.RentalResponseDTO;
import com.app.rentalcar.entity.Rental;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RentalMapper {
    RentalRequestDTO rentalToRentalRequestDTO(Rental rental);
    Rental rentalRequestDTOToRental(RentalRequestDTO rentalRequestDTO);
    RentalResponseDTO rentalToRentalResponseDTO(Rental rental);
}