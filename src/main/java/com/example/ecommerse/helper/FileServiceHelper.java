package com.example.ecommerse.helper;

import com.example.ecommerse.model.entity.File;
import com.example.ecommerse.model.entity.Product;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public interface FileServiceHelper {
    void createDirectories(Path path);
    String getFileName(MultipartFile file);

    File buildFile(String newFileName, Product product);
    List<String> getFilesUris(List<File> files,Long id);
    List<String> getIdAndName(String fileName);
    HttpHeaders buildHeaders(String fileName);
}
