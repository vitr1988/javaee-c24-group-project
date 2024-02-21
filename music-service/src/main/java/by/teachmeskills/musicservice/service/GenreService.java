package by.teachmeskills.musicservice.service;

import by.teachmeskills.musicservice.dto.GenreDto;

import java.util.List;

public interface GenreService {

    List<GenreDto> getAllGenres();

    GenreDto getGenreById(Long id);

    GenreDto save(GenreDto genreDto);

    GenreDto update(GenreDto genreDto, Long id);

    void delete(Long id);

}
