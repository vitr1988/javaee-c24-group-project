package by.teachmeskills.musicservice.mapper;

import by.teachmeskills.musicservice.dto.ArtistDto;
import by.teachmeskills.musicservice.entity.Artist;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ArtistMapper {
    ArtistDto toDTO(Artist model);

    Artist toModel(ArtistDto dto);

    void updateArtistFromDto(ArtistDto dto, @MappingTarget Artist model);
}
