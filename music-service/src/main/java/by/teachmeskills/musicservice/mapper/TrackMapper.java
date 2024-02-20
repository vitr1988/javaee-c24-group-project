package by.teachmeskills.musicservice.mapper;

import by.teachmeskills.musicservice.dto.TrackDto;
import by.teachmeskills.musicservice.entity.Track;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TrackMapper {
    @Mapping(source = "artist", target = "artistDto")
    @Mapping(source = "album", target = "albumDto")
    TrackDto toDTO(Track model);

    @Mapping(source = "artistDto.id", target = "artist.id")
    @Mapping(source = "albumDto.id", target = "album.id")
    Track toModel(TrackDto dto);

    void updateTrackFromDto(TrackDto dto, @MappingTarget Track model);
}
