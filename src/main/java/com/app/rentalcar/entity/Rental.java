package com.app.rentalcar.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name="rentals")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "renter_id")
    private User renter;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;

    private int duration;
    private int totalPriceOfRental;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Car getCar() { return car; }
    public void setCar(Car car) { this.car = car; }

    public User getRenter() { return renter; }
    public void setRenter(User renter) { this.renter = renter; }

    public LocalDateTime getRentalDate() { return rentalDate; }
    public void setRentalDate(LocalDateTime rentalDate) { this.rentalDate = rentalDate; }

    public LocalDateTime getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDateTime returnDate) { this.returnDate = returnDate; }

    public int getDuration() { return duration;}
    public void setDuration(int duration) { this.duration = duration; }

    public int getTotalPriceOfRental() { return totalPriceOfRental; }
    public void setTotalPriceOfRental(int totalPriceOfRental) {this.totalPriceOfRental = totalPriceOfRental; }
}
