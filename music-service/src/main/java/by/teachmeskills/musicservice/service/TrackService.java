package by.teachmeskills.musicservice.service;

import by.teachmeskills.musicservice.dto.TrackDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TrackService {

    List<TrackDto> getAllTracks();

    TrackDto getTrackById(Long id);

    void incrementDownloads(Long id);

    TrackDto save(TrackDto trackDto);

    TrackDto update(TrackDto trackDto, Long id);

    void delete(Long id);
}
