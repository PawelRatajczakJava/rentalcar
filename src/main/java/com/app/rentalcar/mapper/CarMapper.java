package com.app.rentalcar.mapper;

import com.app.rentalcar.dto.CarRequestDTO;
import com.app.rentalcar.dto.CarResponseDTO;
import com.app.rentalcar.entity.Car;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {
    CarRequestDTO carToCarRequestDTO(Car car);
    Car carRequestDTOToCar(CarRequestDTO carRequestDTO);
    CarResponseDTO carToCarResponseDTO(Car car);
}