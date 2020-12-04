package ru.itis.models;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@Builder
@ToString

public class Car {
    private Long id;
    private String mark;
    private String model;
    private Double price;
    private String description;
    private Long mileage;
    private Long markId;
    private Long fileId;
}