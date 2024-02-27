package by.teachmeskills.musicservice.mapper;

import by.teachmeskills.musicservice.dto.AlbumDto;
import by.teachmeskills.musicservice.entity.Album;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AlbumMapper {

    @Mapping(target = "release", expression = "java(albumDto.getRelease() == null ? null :  albumDto.getRelease().atStartOfDay() )")
    @Mapping(target = "tracks", ignore = true)
    Album toEntity(AlbumDto albumDto);

    @AfterMapping
    default void linkTracks(@MappingTarget Album album) {
        if (album.getTracks() != null) {
            album.getTracks().forEach(track -> track.setAlbum(album));
        }
    }

    @Mapping(target = "release", expression = "java(album.getRelease() == null ? null : album.getRelease().toLocalDate())")
    @Mapping(target = "tracks", ignore = true)
    AlbumDto toDto(Album album);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "tracks", ignore = true)
    Album partialUpdate(AlbumDto albumDto, @MappingTarget Album album);
}