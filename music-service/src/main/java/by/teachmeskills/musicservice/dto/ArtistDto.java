package by.teachmeskills.musicservice.dto;

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
    @Size(min = 2, max = 255)
    String name;


    List<AlbumDto> albums;

}