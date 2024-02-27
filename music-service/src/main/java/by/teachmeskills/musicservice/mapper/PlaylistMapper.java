package by.teachmeskills.musicservice.mapper;

import by.teachmeskills.musicservice.dto.PlaylistDto;
import by.teachmeskills.musicservice.entity.Playlist;
import by.teachmeskills.musicservice.entity.Track;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(uses = TrackMapper.class)
public interface PlaylistMapper {

    @Mapping(target = "userId", source = "user.id")
    PlaylistDto toPlaylistDto(Playlist playlist);

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "tracks", expression = "java(mapTrackIdsToTracks(playlistDto.trackIds()))")
    Playlist toPlaylist(PlaylistDto playlistDto);

    default Set<Track> mapTrackIdsToTracks(List<Long> trackIds) {
        return trackIds.stream().map(trackId -> {
                    Track track = new Track();
                    track.setId(trackId);
                    return track;
                })
                .collect(Collectors.toSet());
    }
}
