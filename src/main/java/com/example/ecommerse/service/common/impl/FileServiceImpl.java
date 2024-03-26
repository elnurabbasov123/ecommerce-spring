package com.example.ecommerse.service.common.impl;

import com.example.ecommerse.helper.FileServiceHelper;
import com.example.ecommerse.model.entity.File;
import com.example.ecommerse.model.entity.Product;
import com.example.ecommerse.repository.FileRepository;
import com.example.ecommerse.service.common.FileService;
import com.example.ecommerse.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {
    private final FileServiceHelper fileHelperService;
    private final ProductService productService;
    private final FileRepository fileRepository;

    @Override
    @SneakyThrows
    public ResponseEntity<Void> uploadFile(MultipartFile file, Long id) {
        Product product =  productService.findById(id);

        Path newPath = Paths.get("products\\" + id);

        fileHelperService.createDirectories(newPath);
        String newFileName = fileHelperService.getFileName(file);

        File saveFile = fileHelperService.buildFile(newFileName,product);

        fileRepository.save(saveFile);

        Files.write(newPath.resolve(newFileName),file.getBytes());

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<String>> getFilesByProductId(Long id) {
        Product product = productService.findById(id);
        List<File> filesPath = fileRepository.findAllByProduct(product);

        List<String> filesUris = fileHelperService.getFilesUris(filesPath,id);

        return ResponseEntity.ok(filesUris);
    }

    @Override
    @SneakyThrows
    public ResponseEntity<Resource> download(String fileName) {
        List<String> idAndName = fileHelperService.getIdAndName(fileName);
        Resource resource = new UrlResource(Path.of("products\\"+idAndName.get(0)).toUri().resolve(idAndName.get(1)));

        return new ResponseEntity<>(resource, fileHelperService.buildHeaders(fileName), HttpStatus.OK);
    }

}
