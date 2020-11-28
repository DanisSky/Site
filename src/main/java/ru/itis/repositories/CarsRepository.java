package ru.itis.repositories;

import ru.itis.models.Car;
import ru.itis.models.Mark;

import java.util.Optional;

public interface CarsRepository extends CrudRepository<Car>{
    Optional<Mark> findMarkIdByName(String name);
}
