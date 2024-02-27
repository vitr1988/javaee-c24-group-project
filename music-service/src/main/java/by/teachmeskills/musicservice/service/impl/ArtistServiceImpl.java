package by.teachmeskills.musicservice.service.impl;

import by.teachmeskills.musicservice.dto.ArtistDto;
import by.teachmeskills.musicservice.exception.NotFoundException;
import by.teachmeskills.musicservice.mapper.ArtistMapper;
import by.teachmeskills.musicservice.repository.ArtistRepository;
import by.teachmeskills.musicservice.service.ArtistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ArtistServiceImpl implements ArtistService {

    private final ArtistMapper artistMapper;
    private final ArtistRepository artistsRepository;

    @Override
    public List<ArtistDto> getAllArtists() {
        return artistsRepository.findAll().stream().map(artistMapper::toDto).toList();
    }

    @Override
    public ArtistDto getArtistById(Long id) {
        return artistMapper.toDto(artistsRepository.findById(id).orElseThrow(() -> new NotFoundException("Artist with id {} not found.", id)));
    }


    @Override
    @Transactional
    public ArtistDto save(ArtistDto artistDto) {
        return artistMapper.toDto(artistsRepository.save(artistMapper.toEntity(artistDto.setId(0L))));
    }

    @Override
    @Transactional
    public ArtistDto update(ArtistDto artistDto, Long id) {
        artistsRepository.findById(id).ifPresent(artistEntity -> {
            artistMapper.partialUpdate(artistDto, artistEntity);
            artistsRepository.save(artistEntity);
        });
        return getArtistById(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        artistsRepository.deleteById(id);
    }
}
