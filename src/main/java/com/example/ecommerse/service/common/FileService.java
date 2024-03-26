package com.example.ecommerse.service.common;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    ResponseEntity<Void> uploadFile(MultipartFile file, Long id);

    ResponseEntity<List<String>> getFilesByProductId(Long id);

    ResponseEntity<Resource> download(String fileName);
}
