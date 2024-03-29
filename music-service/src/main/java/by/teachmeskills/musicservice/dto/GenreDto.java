package by.teachmeskills.musicservice.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Accessors(chain = true)
public class GenreDto {

    Long id;

    @NotNull
    @Size(max = 255)
    String name;
}