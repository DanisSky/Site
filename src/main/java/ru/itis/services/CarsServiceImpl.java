package ru.itis.services;

import ru.itis.dto.CarDto;
import ru.itis.models.Car;
import ru.itis.repositories.CarsRepository;

import java.util.List;
import java.util.Optional;

public class CarsServiceImpl implements CarsService {

    private CarsRepository carsRepository;

    public CarsServiceImpl(CarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

    @Override
    public List<CarDto> getAllCars() {
        return CarDto.from(carsRepository.findAll());
    }

    @Override
    public Optional<CarDto> findById(Long id) {
        Optional<Car> car = carsRepository.findById(id);
        return car.map(CarDto::from);
    }

    @Override
    public Long findByMark(String mark) {
        return carsRepository.findMarkIdByName(mark).get().getId();
    }

    @Override
    public void save(Car car) {
        carsRepository.save(car);
    }
}
