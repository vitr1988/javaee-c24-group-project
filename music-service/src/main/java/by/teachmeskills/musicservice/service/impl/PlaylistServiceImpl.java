package by.teachmeskills.musicservice.service.impl;

import by.teachmeskills.musicservice.dto.PlaylistDto;
import by.teachmeskills.musicservice.entity.Playlist;
import by.teachmeskills.musicservice.entity.Track;
import by.teachmeskills.musicservice.exception.NotFoundException;
import by.teachmeskills.musicservice.mapper.PlaylistMapper;
import by.teachmeskills.musicservice.repository.PlaylistRepository;
import by.teachmeskills.musicservice.repository.PlaylistTrackRepository;
import by.teachmeskills.musicservice.repository.TrackRepository;
import by.teachmeskills.musicservice.repository.UserRepository;
import by.teachmeskills.musicservice.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistRepository playlistRepository;

    private final UserRepository userRepository;

    private final TrackRepository trackRepository;

    private final PlaylistTrackRepository playlistTrackRepository;

    private final PlaylistMapper playlistMapper;

    @Override
    @Transactional(readOnly = true)
    public List<PlaylistDto> findAll() {
        return playlistRepository.findAll()
                .stream().map(playlistMapper::toPlaylistDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PlaylistDto findById(Long id) {
        return playlistRepository.findById(id)
                .map(playlistMapper::toPlaylistDto)
                .orElseThrow(() -> new NotFoundException("Playlist with id {} not found", id));
    }

    @Override
    @Transactional
    public PlaylistDto save(PlaylistDto playlistDto) {
        if (!userRepository.existsById(playlistDto.userId())) {
            throw new NotFoundException("User with id {} not found", playlistDto.userId());
        }
        playlistDto.trackIds()
                .forEach(trackId -> {
                    if (!trackRepository.existsById(trackId)) {
                        throw new NotFoundException("Track with id {} not found", trackId);
                    }
                });
        Playlist playlist = playlistMapper.toPlaylist(playlistDto);
        return playlistMapper.toPlaylistDto(playlistRepository.save(playlist));
    }

    @Override
    @Transactional
    public void changeStateOfTrackInPlaylist(Long playlistId, Long trackId) {
        Playlist playlist = playlistRepository
                .findById(playlistId)
                .orElseThrow(() -> new NotFoundException("Playlist with id {} not found", playlistId));
        Track track = trackRepository
                .findById(trackId)
                .orElseThrow(() -> new NotFoundException("Track with id {} not found", trackId));
        playlistTrackRepository.findByIdPlaylistIdAndIdTrackId(playlistId, trackId)
                .ifPresentOrElse(playlistTrackRepository::delete, () ->
                        playlist.getTracks().add(track));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        playlistRepository.findById(id).ifPresentOrElse(
                playlistRepository::delete, () -> {
                    throw new NotFoundException("Playlist with id {} not found", id);
                });
    }
}
