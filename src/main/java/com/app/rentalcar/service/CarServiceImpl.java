package com.app.rentalcar.service;

import com.app.rentalcar.dto.CarRequestDTO;
import com.app.rentalcar.dto.CarResponseDTO;
import com.app.rentalcar.entity.Car;
import com.app.rentalcar.exception.CarNotFoundException;
import com.app.rentalcar.mapper.CarMapper;
import com.app.rentalcar.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarMapper carMapper;

    @Override
    public List<CarResponseDTO> getAllCars() {
        return carRepository.findAll()
                .stream()
                .map(carMapper::carToCarResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CarResponseDTO addCar(CarRequestDTO carRequestDTO) {
        Car car = carMapper.carRequestDTOToCar(carRequestDTO);
        car.setAvailable(true);
        Car savedCar = carRepository.save(car);
        return carMapper.carToCarResponseDTO(savedCar);
    }

    @Override
    public CarResponseDTO updateCar(Long id, CarRequestDTO carRequestDTO) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("Car not found with id " + id));
        car.setMake(carRequestDTO.getMake());
        car.setModel(carRequestDTO.getModel());
        car.setYear(carRequestDTO.getYear());
        car.setAvailable(carRequestDTO.isAvailable());
        Car updatedCar = carRepository.save(car);
        return carMapper.carToCarResponseDTO(updatedCar);
    }


    @Override
    public void deleteCar(Long id) {
        if (!carRepository.existsById(id)) {
            throw new CarNotFoundException("Car not found with id " + id);
        }
        carRepository.deleteById(id);
    }
}
