package by.teachmeskills.musicservice.service;

import by.teachmeskills.musicservice.dto.ArtistDto;

import java.util.List;

public interface ArtistService {
    List<ArtistDto> getAllArtists();

    ArtistDto getArtistById(Long id);

    ArtistDto save(ArtistDto artistDto);

    ArtistDto update(ArtistDto artistDto, Long id);

    void delete(Long id);
}
