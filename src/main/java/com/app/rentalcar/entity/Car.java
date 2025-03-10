package com.app.rentalcar.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String make;
    private String model;
    private int year;
    private boolean available;
    private int buyPrice;
    private int rentPriceForDay;


    @ManyToOne
    @JoinColumn(name = "rented_by")
    private User rentedBy;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getMake() {
        return make;
    }
    public void setMake(String make) {
        this.make = make;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
    public int getBuyPrice() {return buyPrice;}
    public void setBuyPrice(int buyPrice) {this.buyPrice = buyPrice;}
    public int getRentPriceForDay() {return rentPriceForDay;}
    public void setRentPriceForDay(int rentPriceForDay) {this.rentPriceForDay = rentPriceForDay;}
    public User getRentedBy() { return rentedBy; }
    public void setRentedBy(User rentedBy) { this.rentedBy = rentedBy; }
}
