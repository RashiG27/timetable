package com.abcd.timetable;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@Slf4j
@RequestMapping("/")
public class Controller {

    @Autowired
    ServiceImpl service;

    @GetMapping("/downloadTemplate")
    public ResponseEntity<APIResponse> downloadTemplate() throws IOException {
        return new ResponseEntity<>(APIResponse.getSuccessResponse(service.downloadTemplate()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<APIResponse> uploadTimeTable(@RequestPart(name = "file") MultipartFile multipartFile){
        if(null == multipartFile || multipartFile.isEmpty()){
            throw new TimeTableException("No files found at the location");
        }
        return new ResponseEntity<>(APIResponse.getSuccessResponse(service.uploadTimeTable(multipartFile)), HttpStatus.OK);
    }
}
