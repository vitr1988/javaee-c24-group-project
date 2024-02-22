package by.teachmeskills.musicservice.service;

import by.teachmeskills.musicservice.dto.PlaylistDto;

import java.util.List;

public interface PlaylistService {

    List<PlaylistDto> findAll();

    PlaylistDto findById(Long id);

    PlaylistDto save(PlaylistDto playlistDto);

    void changeStateOfTrackInPlaylist(Long playlistId, Long trackId);

    void delete(Long id);
}
