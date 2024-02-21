package by.teachmeskills.musicservice.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
public class AlbumDto {

    Long id;

    @NotNull
    @Size(min = 1, max = 255)
    @NotEmpty
    @NotBlank
    String title;

    LocalDateTime release;

    @NotNull
    ArtistDto artist;

    List<TrackDto> tracks;
}