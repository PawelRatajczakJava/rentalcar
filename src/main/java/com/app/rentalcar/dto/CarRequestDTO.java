package com.app.rentalcar.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;


public class CarRequestDTO {

    @NotBlank(message = "Maker can't be blank")
    @Length(max=50, message = "Max 50 characters")
    private String make;

    @NotBlank(message = "Model can't be blank")
    @Length(max=50, message = "Max 50 characters")
    private String model;

    @NotNull(message = "Year of production can't be null")
    @Range(min = 1886, max = 2025)
    private Integer year;

    private boolean available;

    public String getMake() { return make; }
    public void setMake(String make) { this.make = make; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}