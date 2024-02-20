package by.teachmeskills.musicservice.service;

import by.teachmeskills.musicservice.dto.AlbumDto;

import java.util.List;

public interface AlbumService {
    List<AlbumDto> getAllAlbums();

    AlbumDto getAlbumById(Long id);

    AlbumDto save(AlbumDto albumDto);

    AlbumDto update(AlbumDto albumDto, Long id);

    void delete(Long id);
}
