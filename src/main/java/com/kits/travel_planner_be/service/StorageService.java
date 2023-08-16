package com.kits.travel_planner_be.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {
    String uploadImage(MultipartFile file) throws IOException;
    byte[] downloadImage(String fileName);
}
