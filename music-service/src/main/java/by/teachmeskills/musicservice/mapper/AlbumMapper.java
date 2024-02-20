package by.teachmeskills.musicservice.mapper;

import by.teachmeskills.musicservice.dto.AlbumDto;
import by.teachmeskills.musicservice.entity.Album;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AlbumMapper {
    @Mapping(source = "artist", target = "artistDto")
    AlbumDto toDTO(Album model);

    @Mapping(source = "artistDto.id", target = "artist.id")
    Album toModel(AlbumDto dto);

    void updateAlbumFromDto(AlbumDto dto, @MappingTarget Album model);
}
