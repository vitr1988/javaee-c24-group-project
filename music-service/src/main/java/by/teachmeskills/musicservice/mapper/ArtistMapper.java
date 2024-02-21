package by.teachmeskills.musicservice.mapper;

import by.teachmeskills.musicservice.dto.ArtistDto;
import by.teachmeskills.musicservice.entity.Artist;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ArtistMapper {
    Artist toEntity(ArtistDto artistDto);

    @AfterMapping
    default void linkAlbums(@MappingTarget Artist artist) {
        artist.getAlbums().forEach(album -> album.setArtist(artist));
    }

    ArtistDto toDto(Artist artist);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Artist partialUpdate(ArtistDto artistDto, @MappingTarget Artist artist);
}