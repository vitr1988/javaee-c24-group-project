package by.teachmeskills.musicservice.mapper;


import by.teachmeskills.musicservice.dto.TrackDto;
import by.teachmeskills.musicservice.entity.Track;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = TrackMapper.class)
public interface TrackListMapper {

    List<TrackDto> toDto(List<Track> trackList);

    List<Track> toEntity(List<TrackDto> trackDtoList);
}
