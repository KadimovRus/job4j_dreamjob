package dreamjob.service;

import dreamjob.dto.FileDto;
import dreamjob.model.File;

import java.util.Optional;

public interface FileService {
    File save(FileDto fileDto);

    Optional<FileDto> getFileById(int id);

    void deleteById(int id);
}
