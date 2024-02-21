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
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {

    private final AlbumMapper albumMapper;
    private final AlbumRepository albumRepository;

    @Override
    public List<AlbumDto> getAllAlbums() {
        return albumRepository.findAll().stream().map(albumMapper::toDto).toList();
    }

    @Override
    public AlbumDto getAlbumById(Long id) {
        return albumMapper.toDto(albumRepository.findById(id).orElseThrow(() -> new NotFoundException("Album with id {} not found.", id)));
    }

    @Transactional
    @Override
    public AlbumDto save(AlbumDto albumDto) {
        return albumMapper.toDto(albumRepository.save(albumMapper.toEntity(albumDto)));
    }

    @Transactional
    @Override
    public AlbumDto update(AlbumDto albumDto, Long id) {
        albumRepository.findById(id).ifPresent(albumEntity -> {
            albumMapper.partialUpdate(albumDto, albumEntity);
            albumRepository.save(albumMapper.toEntity(albumDto));
        });
        return getAlbumById(id);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        albumRepository.deleteById(id);
    }
}
