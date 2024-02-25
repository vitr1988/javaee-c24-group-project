package by.teachmeskills.musicservice.service.impl;

import by.teachmeskills.musicservice.dto.TrackDto;
import by.teachmeskills.musicservice.entity.TrackFile;
import by.teachmeskills.musicservice.mapper.TrackMapper;
import by.teachmeskills.musicservice.repository.TrackFileRepository;
import by.teachmeskills.musicservice.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    @Value(value = "${storage.music_files_path}")
    private String path;

    private final TrackMapper trackMapper;
    private final TrackFileRepository trackFileRepository;

    @Override
    public Boolean uploadFile(MultipartFile file, TrackDto trackDto) {
        try {
            String filePath = path + file.getOriginalFilename();

            Path path = Paths.get(filePath);


            Files.write(path, file.getBytes());

            TrackFile trackFile = new TrackFile();
            trackFile.setUploadedName(file.getOriginalFilename());
            trackFile.setStoreName(file.getOriginalFilename());
            trackFile.setSize(file.getSize());
            trackFile.setTrack(trackMapper.toEntity(trackDto));

            trackFileRepository.save(trackFile);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Resource downloadFile(Long id) {
        String filePath = path + trackFileRepository.findTrackFileByTrackId(id).getStoreName();
        try {
            return new UrlResource(Paths.get(filePath).toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean removeFile(Long id) {
        TrackFile trackFile = trackFileRepository.findTrackFileByTrackId(id);
        String filePath = path + trackFile.getStoreName();

        Path path = Paths.get(filePath);
        try {
            Files.delete(path);
            log.info("Файл удален: {}", path);
        } catch (IOException e) {
            log.error("Ошибка удаления файла: ", e);
        }

        trackFileRepository.deleteById(trackFile.getId());
        log.info("Файл удален из базы в базе данных");
        return true;
    }
}
