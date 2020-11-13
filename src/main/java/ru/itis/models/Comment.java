package ru.itis.models;

import lombok.*;

@Getter
@EqualsAndHashCode
@Builder
@ToString
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private Long id;
    private Long userId;
    private String text;
    private String data;
    private Long carId;
}
