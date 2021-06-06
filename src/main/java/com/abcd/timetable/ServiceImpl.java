package com.abcd.timetable;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

@Service
@Slf4j
public class ServiceImpl {
    public byte [] downloadTemplate() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("/downloadTemplate.xlsx");
        return inputStream.readAllBytes();
    }

    public byte[] uploadTimeTable(MultipartFile multipartFile) {
        try {
            File file = convertMultipartToFile(multipartFile);
            return processFile(file);
        } catch (Exception e) {
            log.error("Error occurred while uploading the file: {}", e.getMessage());
            throw new TimeTableException(e.getMessage());
        }
    }

    private byte[] processFile(File file) throws IOException {
        return Files.readAllBytes(file.toPath());
    }

    private File convertMultipartToFile(MultipartFile multipartFile) {
        File convertedFile = null;
        try{
            convertedFile = new File(System.getProperty("java.io.tmpdir") + System.getProperty("file.seperator")
                    + multipartFile.getOriginalFilename());
            multipartFile.transferTo(convertedFile);
        } catch (IOException e) {
            log.error("Error in converting multipart file to file {}", e.getMessage(), e);
            throw new TimeTableException(e.getMessage());
        }
        return convertedFile;
    }
}
