package by.teachmeskills.musicservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.List;

public record PlaylistDto(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY) Long id,
        @NotBlank String title,
        @NotBlank String description,
        @NotNull Long userId,
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) @NotNull List<Long> trackIds,
        @JsonProperty(access = JsonProperty.Access.READ_ONLY) List<TrackDto> tracks
) {

}
