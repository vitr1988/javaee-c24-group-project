package by.teachmeskills.musicservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArtistDto {
    private Long id;
    private String name;
    private LocalDateTime updatedAt;
}
