package by.teachmeskills.musicservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
public class TrackDto {

    Long id;

    @NotNull
    @Size(min = 2, max = 255)
    @NotEmpty
    @NotBlank
    String title;

    @NotNull
    Duration trackLength;

    @NotNull
    LocalDateTime trackBirthday;

    @Size(max = 5000)
    String text;

    @JsonIgnore
    AlbumDto album;

    Long downloads;

    List<GenreDto> genres;
}