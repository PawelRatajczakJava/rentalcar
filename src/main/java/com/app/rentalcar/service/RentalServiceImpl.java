package com.app.rentalcar.service;

import com.app.rentalcar.dto.RentalRequestDTO;
import com.app.rentalcar.dto.RentalResponseDTO;
import com.app.rentalcar.entity.Car;
import com.app.rentalcar.entity.Rental;
import com.app.rentalcar.entity.User;
import com.app.rentalcar.exception.CarNotFoundException;
import com.app.rentalcar.exception.RentalException;
import com.app.rentalcar.mapper.RentalMapper;
import com.app.rentalcar.repository.CarRepository;
import com.app.rentalcar.repository.RentalRepository;
import com.app.rentalcar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalServiceImpl implements RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RentalMapper rentalMapper;


    @Transactional
    @Override
    public RentalResponseDTO rentCar(@RequestBody Long carId, String renterUsername) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new CarNotFoundException("Car not found with id " + carId));
        if (!car.isAvailable()) {
            throw new RentalException("Car is already rented");
        }
        User user = userRepository.findByUsername(renterUsername)
                .orElseThrow(() -> new RentalException("User not found"));
        car.setAvailable(false);
        carRepository.save(car);
        Rental rental = new Rental();
        rental.setCar(car);
        rental.setRenter(user);
        rental.setRentalDate(LocalDateTime.now());
        rental.setReturnDate(rental.getRentalDate());
        rental.setDuration((int)ChronoUnit.DAYS.between(rental.getRentalDate(), LocalDateTime.now()));
        rental.setTotalPriceOfRental(car.getRentPriceForDay()* (int)ChronoUnit.DAYS.between(rental.getRentalDate(), LocalDateTime.now()));
        Rental savedRental = rentalRepository.save(rental);
        return rentalMapper.rentalToRentalResponseDTO(savedRental);
    }

    @Override
    public RentalResponseDTO returnCar(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new RentalException("Rental not found with id " + rentalId));
        if (rental.getReturnDate() != null) {
            throw new RentalException("Car is already returned");
        }
        rental.setReturnDate(LocalDateTime.now());

        Car car = rental.getCar();
        car.setAvailable(true);
        carRepository.save(car);

        Rental updatedRental = rentalRepository.save(rental);
        return rentalMapper.rentalToRentalResponseDTO(updatedRental);
    }

    @Override
    public List<RentalResponseDTO> getAllRentals() {
        return rentalRepository.findAll().stream()
                .map(rentalMapper::rentalToRentalResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RentalResponseDTO> getRentalsByUser(String username) {
        List<Rental> rentals = rentalRepository.findAll();
        return rentals.stream()
                .filter(r -> r.getRenter().getUsername().equals(username))
                .map(rentalMapper::rentalToRentalResponseDTO)
                .collect(Collectors.toList());
    }


}