package com.kits.travel_planner_be.service.impl;

import java.io.IOException;
import java.util.Optional;

import com.kits.travel_planner_be.model.ImageData;
import com.kits.travel_planner_be.repository.StorageRepository;
import com.kits.travel_planner_be.service.StorageService;
import com.kits.travel_planner_be.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageRepository storageRepository;

    private static final String IMAGE_URL = "http://localhost:8080/image/";

    @Override
    public String uploadImage(MultipartFile file) throws IOException {
        ImageData imageData = storageRepository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
        if (imageData != null) {
            return IMAGE_URL + file.getOriginalFilename();
        }
        return null;
    }

    @Override
    public byte[] downloadImage(String fileName) {
        Optional<ImageData> dbImageData = storageRepository.findByName(fileName);
        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }
}