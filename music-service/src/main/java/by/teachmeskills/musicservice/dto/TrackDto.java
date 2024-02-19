package by.teachmeskills.musicservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TrackDto {
    private Long id;
    private String title;
    private LocalDateTime track_length;
    private LocalDateTime created_at;
    private String text;
    private ArtistDto artistDto;
    private LocalDateTime updatedAt;
}
