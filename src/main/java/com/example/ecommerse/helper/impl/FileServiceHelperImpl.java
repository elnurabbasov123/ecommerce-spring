package com.example.ecommerse.helper.impl;

import com.example.ecommerse.helper.FileServiceHelper;
import com.example.ecommerse.model.entity.File;
import com.example.ecommerse.model.entity.Product;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
@RequiredArgsConstructor
public class FileServiceHelperImpl implements FileServiceHelper {
    @Value(value = "${server.custom.protocol}")
    private String PROTOCOL;
    @Value(value = "${server.custom.address}")
    private String SERVER_ADDRESS;
    @Value(value = "${server.custom.port}")
    private String SERVER_PORT;
    @Value("${server.custom.methode.upload.file.uri}")
    private String URI;


    @Override
    @SneakyThrows
    public void createDirectories(Path path) {
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }
    }

    private String createFileName(String format) {

        return UUID.randomUUID().toString().substring(0, 4) +
                "." +
                format;
    }

    public String getFileName(MultipartFile file) {
        String fileOriginal = file.getOriginalFilename();
        assert  fileOriginal != null;
        String[] split = fileOriginal.split("\\.");
        String format = split[1];

        return createFileName(format);
    }

    @Override
    public File buildFile(String newFileName, Product product) {
        return File.builder()
                .filePath(newFileName)
                .product(product)
                .build();
    }

    private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) (Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))).getRequest();

    }

    public List<String> getFilesUris(List<File> files, Long id) {

        List<String> paths = new ArrayList<>(files.size());
        files
                .forEach(file ->
                        paths.add(PROTOCOL +
                                SERVER_ADDRESS +
                                SERVER_PORT +
                                URI +
                                id + "_" +
                                file.getFilePath()));

        return paths;
    }

    public List<String> getIdAndName(String fileName) {
        String[]split = fileName.split("_");
        String id = split[0];
        String name = split[1];

        return List.of(id,name);
    }
    public HttpHeaders buildHeaders(String fileName){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentDispositionFormData("attachment",fileName);

        return httpHeaders;
    }

}
