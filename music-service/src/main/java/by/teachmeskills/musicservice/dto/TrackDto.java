package by.teachmeskills.musicservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Data
@Accessors(chain = true)
public class TrackDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long id;

    @NotNull
    @Size(min = 2, max = 255)
    @NotEmpty
    @NotBlank
    String title;

    @NotNull
    Long length;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate release;

    @JsonIgnoreProperties({"tracks"})
    @NotNull
    AlbumDto album;

    @Size(max = 5000)
    String text;

    Long downloads;

    Set<GenreDto> genres;
}