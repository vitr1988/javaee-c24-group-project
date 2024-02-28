package by.teachmeskills.musicservice.mapper;

import by.teachmeskills.musicservice.dto.AlbumDto;
import by.teachmeskills.musicservice.entity.Album;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {TrackMapperWithoutAlbums.class, ArtistMapperWithoutAlbums.class})
public interface AlbumMapperWithoutTracks {

    @Mapping(target = "tracks", ignore = true)
    Album toEntity(AlbumDto albumDto);

    @Mapping(target = "tracks", ignore = true)
    AlbumDto toDto(Album album);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "tracks", ignore = true)
    Album partialUpdate(AlbumDto albumDto, @MappingTarget Album album);

}
