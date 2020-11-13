package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.Car;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    private Long id;
    private String mark;
    private String model;
    private Double price;
    private String description;
    private Long mileage;
    private String fileStorageName;
    private String fileType;
    public static CarDto from(Car car) {
        return CarDto.builder()
                .id(car.getId())
                .mark(car.getMark())
                .model(car.getModel())
                .price(car.getPrice())
                .description(car.getDescription())
                .mileage(car.getMileage())
                .fileStorageName(car.getStorageFileName())
                .fileType(car.getFileType())
                .build();
    }

    public static List<CarDto> from(List<Car> cars){
        return cars.stream().map(CarDto::from).collect(Collectors.toList());
    }

}
