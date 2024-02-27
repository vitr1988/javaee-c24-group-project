package by.teachmeskills.musicservice.mapper;

import by.teachmeskills.musicservice.dto.PlaylistDto;
import by.teachmeskills.musicservice.entity.Playlist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = TrackMapper.class)
public interface PlaylistMapper {

    @Mapping(target = "userId", source = "user.id")
    PlaylistDto toPlaylistDto(Playlist playlist);

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "tracks", source = "trackIds")
    Playlist toPlaylist(PlaylistDto playlistDto);
}
