package ru.itis.models;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@Builder
@ToString
public class Mark {
    private Long id;
    private String mark;
}
