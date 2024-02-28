package by.teachmeskills.musicservice.service.impl;

import by.teachmeskills.musicservice.dto.TrackDto;
import by.teachmeskills.musicservice.exception.NotFoundException;
import by.teachmeskills.musicservice.mapper.TrackMapper;
import by.teachmeskills.musicservice.repository.TrackRepository;
import by.teachmeskills.musicservice.service.TrackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TrackServiceImpl implements TrackService {

    private final TrackMapper trackMapper;
    private final TrackRepository tracksRepository;

    @Override
    public List<TrackDto> getAllTracks() {
        return tracksRepository.findAll().stream().map(trackMapper::toDto).toList();
    }

    @Override
    public TrackDto getTrackById(Long id) {
        return trackMapper.toDto(tracksRepository.findById(id).orElseThrow(() -> new NotFoundException("Track with id {} not found.", id)));
    }


    @Override
    @Transactional
    public void incrementDownloads(Long id){
        tracksRepository.findById(id).ifPresent(track -> {
            Long downloads = track.getDownloads();
            track.setDownloads(downloads == null ? 1L : downloads + 1L);
            tracksRepository.save(track);
        });
    }

    @Override
    @Transactional
    public TrackDto save(TrackDto trackDto) {
        return trackMapper.toDto(tracksRepository.save(trackMapper.toEntity(trackDto.setId(0L))));
    }

    @Override
    @Transactional
    public TrackDto update(TrackDto trackDto, Long id) {
        tracksRepository.findById(id).ifPresent(trackEntity -> {
            trackMapper.partialUpdate(trackDto, trackEntity);
            tracksRepository.save(trackEntity);
        });
        return getTrackById(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        tracksRepository.deleteById(id);
    }
}
