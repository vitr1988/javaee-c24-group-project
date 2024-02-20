package by.teachmeskills.musicservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AlbumDto {
    private Long id;
    private String title;
    private LocalDateTime album_release;
    private ArtistDto artistDto;
    private LocalDateTime updatedAt;
}
