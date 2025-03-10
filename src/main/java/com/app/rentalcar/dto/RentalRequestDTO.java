package com.app.rentalcar.dto;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class RentalRequestDTO {

    private Long carId;
    private String renterUsername;
    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;


    public Long getCarId() { return carId; }
    public void setCarId(Long carId) { this.carId = carId; }

    public String getRenterUsername() { return renterUsername; }
    public void setRenterUsername(String renterUsername) { this.renterUsername = renterUsername; }

    public LocalDateTime getRentalDate() { return rentalDate; }
    public void setRentalDate(LocalDateTime rentalDate) { this.rentalDate = rentalDate; }

    public LocalDateTime getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDateTime returnDate) { this.returnDate = returnDate; }

    public int getDuration() { return (int) ChronoUnit.DAYS.between(rentalDate, returnDate);}

}
