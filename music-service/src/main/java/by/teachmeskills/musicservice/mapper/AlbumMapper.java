package by.teachmeskills.musicservice.mapper;

import by.teachmeskills.musicservice.dto.AlbumDto;
import by.teachmeskills.musicservice.entity.Album;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {TrackMapper.class})
public interface AlbumMapper {
    Album toEntity(AlbumDto albumDto);

    @AfterMapping
    default void linkTracks(@MappingTarget Album album) {
        album.getTracks().forEach(track -> track.setAlbum(album));
    }

    AlbumDto toDto(Album album);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Album partialUpdate(AlbumDto albumDto, @MappingTarget Album album);
}