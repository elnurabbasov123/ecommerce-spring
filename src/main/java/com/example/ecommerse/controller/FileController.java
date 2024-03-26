package com.example.ecommerse.controller;

import com.example.ecommerse.service.common.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;
    @PostMapping("/{id}")
    public ResponseEntity<Void> uploadFile(@RequestPart(value = "file") MultipartFile file,
                                           @PathVariable("id") Long id){
        return fileService.uploadFile(file,id);
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<String>> getFilesByProductId(@PathVariable("id") Long id){
        return fileService.getFilesByProductId(id);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> download(@PathVariable("fileName") String fileName){
        return fileService.download(fileName);
    }

}
