package by.teachmeskills.musicservice.dto;

import by.teachmeskills.musicservice.entity.Album;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Accessors(chain = true)
public class ArtistDto {

    Long id;

    @NotNull
    @Size(max = 255)
    String name;
}