package by.teachmeskills.musicservice.service.impl;

import by.teachmeskills.musicservice.dto.GenreDto;
import by.teachmeskills.musicservice.exception.NotFoundException;
import by.teachmeskills.musicservice.mapper.GenreMapper;
import by.teachmeskills.musicservice.repository.GenreRepository;
import by.teachmeskills.musicservice.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreMapper genreMapper;
    private final GenreRepository genreRepository;

    @Override
    public List<GenreDto> getAllGenres() {
        return genreRepository.findAll().stream().map(genreMapper::toDto).toList();
    }

    @Override
    public GenreDto getGenreById(Long id) {
        return genreMapper.toDto(genreRepository.findById(id).orElseThrow(() -> new NotFoundException("Genre with id {} not found.", id)));
    }

    @Override
    @Transactional
    public GenreDto save(GenreDto genreDto) {
        return genreMapper.toDto(genreRepository.save(genreMapper.toEntity(genreDto.setId(0L))));
    }

    @Override
    @Transactional
    public GenreDto update(GenreDto genreDto, Long id) {
        genreRepository.findById(id).ifPresent(genreEntity -> {
            genreMapper.partialUpdate(genreDto, genreEntity);
            genreRepository.save(genreEntity);
        });
        return getGenreById(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        genreRepository.deleteById(id);
    }
}
