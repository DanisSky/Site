package ru.itis.models;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FileInfo {
    private Long id;
    private String originalFileName;
    private UUID storageFileName;
    private Long size;
    private String type;
}
