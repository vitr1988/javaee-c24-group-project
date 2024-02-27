package by.teachmeskills.musicservice.mapper;

import by.teachmeskills.musicservice.dto.TrackDto;
import by.teachmeskills.musicservice.entity.Track;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.time.Duration;
import java.util.stream.Collectors;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, imports = {Duration.class})
public interface TrackMapper {

    @Mappings({
            @Mapping(target = "length", expression = "java(Duration.ofSeconds(trackDto.getLength()))"),
            @Mapping(target = "release", expression = "java(trackDto.getRelease().atStartOfDay())"),
            @Mapping(target = "album", ignore = true)
    })
    Track toEntity(TrackDto trackDto);

    @Mappings({
            @Mapping(target = "length", expression = "java(track.getLength().getSeconds())"),
            @Mapping(target = "release", expression = "java(track.getRelease().toLocalDate())"),
            @Mapping(target = "album", ignore = true)
    })

    TrackDto toDto(Track track);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "length", expression = "java(Duration.ofSeconds(trackDto.getLength()))"),
            @Mapping(target = "release", expression = "java(trackDto.getRelease().atStartOfDay())"),
            @Mapping(target = "album", ignore = true)
    })
    Track partialUpdate(TrackDto trackDto, @MappingTarget Track track);

    @AfterMapping
    default void setAlbumToDto(Track track, @MappingTarget TrackDto trackDto) {
        AlbumMapper albumMapper = Mappers.getMapper(AlbumMapper.class);
        GenreMapper genreMapper = Mappers.getMapper(GenreMapper.class);

        trackDto.setGenres(track.getGenres().stream().map(genreMapper::toDto).collect(Collectors.toSet()));
        trackDto.setAlbum(albumMapper.toDto(track.getAlbum()));
    }

    @AfterMapping
    default void setAlbumToEntity(TrackDto trackDto, @MappingTarget Track track) {
        AlbumMapper albumMapper = Mappers.getMapper(AlbumMapper.class);
        GenreMapper genreMapper = Mappers.getMapper(GenreMapper.class);

        track.setAlbum(albumMapper.toEntity(trackDto.getAlbum()));
        track.setGenres(trackDto.getGenres().stream().map(genreMapper::toEntity).collect(Collectors.toSet()));

    }


}