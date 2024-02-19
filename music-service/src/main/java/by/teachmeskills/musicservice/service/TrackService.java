package by.teachmeskills.musicservice.service;

import by.teachmeskills.musicservice.dto.TrackDto;

import java.util.List;

public interface TrackService {

    List<TrackDto> getAllTracks();

    TrackDto getTrackById(Long id);

    TrackDto save(TrackDto trackDto);

    TrackDto update(TrackDto trackDto, Long id);

    void delete(Long id);
}
