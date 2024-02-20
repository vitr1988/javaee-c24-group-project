package by.teachmeskills.musicservice.service.impl;

import by.teachmeskills.musicservice.dto.AlbumDto;
import by.teachmeskills.musicservice.exception.NotFoundException;
import by.teachmeskills.musicservice.mapper.AlbumMapper;
import by.teachmeskills.musicservice.repository.AlbumRepository;
import by.teachmeskills.musicservice.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {

    private final AlbumMapper albumMapper;
    private final AlbumRepository albumRepository;

    @Override
    public List<AlbumDto> getAllAlbums() {
        return albumRepository.findAll().stream().map(albumMapper::toDTO).toList();
    }

    @Override
    public AlbumDto getAlbumById(Long id) {
        return albumMapper.toDTO(albumRepository.findById(id).orElseThrow(() -> new NotFoundException("Album with id {} not found.", id)));
    }

    @Override
    public AlbumDto save(AlbumDto albumDto) {
        return albumMapper.toDTO(albumRepository.save(albumMapper.toModel(albumDto)));
    }

    @Override
    public AlbumDto update(AlbumDto albumDto, Long id) {
        albumRepository.findById(id).ifPresent(a -> {
            albumMapper.updateAlbumFromDto(albumDto, a);
            albumRepository.save(a);
        });
        return getAlbumById(id);
    }

    @Override
    public void delete(Long id) {
        albumRepository.deleteById(id);
    }
}
