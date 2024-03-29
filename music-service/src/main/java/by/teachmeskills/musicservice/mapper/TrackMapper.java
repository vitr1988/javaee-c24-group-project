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


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, imports = {Duration.class}, uses = {AlbumMapperWithoutTracks.class, GenreMapper.class})
public interface TrackMapper {

    @Mappings({
            @Mapping(target = "length", expression = "java(trackDto.getLength() == null ? null : Duration.ofSeconds(trackDto.getLength()))"),
            @Mapping(target = "release", expression = "java(trackDto.getRelease() == null ? null : trackDto.getRelease().atStartOfDay())")
    })
    Track toEntity(TrackDto trackDto);

    @Mappings({
            @Mapping(target = "length", expression = "java(track.getLength() == null ? null : track.getLength().getSeconds())"),
            @Mapping(target = "release", expression = "java(track.getRelease() == null ? null : track.getRelease().toLocalDate())")
    })
    TrackDto toDto(Track track);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "length", expression = "java(trackDto.getLength() == null ? null : Duration.ofSeconds(trackDto.getLength()))"),
            @Mapping(target = "release", expression = "java(trackDto.getRelease() == null ? null : trackDto.getRelease().atStartOfDay())")
    })
    Track partialUpdate(TrackDto trackDto, @MappingTarget Track track);

}