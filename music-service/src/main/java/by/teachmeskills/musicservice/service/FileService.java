package by.teachmeskills.musicservice.service;

import by.teachmeskills.musicservice.dto.TrackDto;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    Boolean uploadFile(MultipartFile file, TrackDto trackDto);

    Resource downloadFile(Long id);

    Boolean removeFile(Long id);
}
