package by.teachmeskills.musicservice.service.impl;

import by.teachmeskills.musicservice.dto.ArtistDto;
import by.teachmeskills.musicservice.exception.NotFoundException;
import by.teachmeskills.musicservice.mapper.ArtistMapper;
import by.teachmeskills.musicservice.repository.ArtistsRepository;
import by.teachmeskills.musicservice.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ArtistsServiceImpl implements ArtistService {

    private final ArtistMapper artistMapper;
    private final ArtistsRepository artistsRepository;

    @Override
    public List<ArtistDto> getAllArtists() {
        return artistsRepository.findAll().stream().map(artistMapper::toDTO).toList();
    }

    @Override
    public ArtistDto getArtistById(Long id) {
        return artistMapper.toDTO(artistsRepository.findById(id).orElseThrow(() -> new NotFoundException("Artist with id {} not found.", id)));
    }

    @Override
    public ArtistDto save(ArtistDto artistDto) {
        return artistMapper.toDTO(artistsRepository.save(artistMapper.toModel(artistDto)));
    }

    @Override
    public ArtistDto update(ArtistDto artistDto, Long id) {
        artistsRepository.findById(id).ifPresent(a -> {
            artistMapper.updateArtistFromDto(artistDto, a);
            artistsRepository.save(a);
        });
        return getArtistById(id);
    }

    @Override
    public void delete(Long id) {
        artistsRepository.deleteById(id);
    }
}
