package ru.itis.services;

import ru.itis.dto.CarDto;
import ru.itis.models.Car;

import java.util.List;
import java.util.Optional;

public interface CarsService {
    List<CarDto> getAllCars();
    Optional<CarDto> findById(Long id);
    Optional<Car> findByMark(String mark);
}
