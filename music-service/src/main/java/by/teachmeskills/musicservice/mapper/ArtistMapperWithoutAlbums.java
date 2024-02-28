package by.teachmeskills.musicservice.mapper;

import by.teachmeskills.musicservice.dto.ArtistDto;
import by.teachmeskills.musicservice.entity.Artist;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = AlbumMapperWithoutArtist.class)
public interface ArtistMapperWithoutAlbums {

    @Mapping(target = "albums", ignore = true)
    Artist toEntity(ArtistDto artistDto);

    @Mapping(target = "albums", ignore = true)
    ArtistDto toDto(Artist artist);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "albums", ignore = true)
    Artist partialUpdate(ArtistDto artistDto, @MappingTarget Artist artist);
}
