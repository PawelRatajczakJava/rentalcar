package com.app.rentalcar.dto;


import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
public class RentalResponseDTO {

    private Long carId;
    private String renterUsername;
    private int duration;
    private int totalPriceOfRental;
}
