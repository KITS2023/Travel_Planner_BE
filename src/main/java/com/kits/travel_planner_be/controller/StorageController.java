package com.kits.travel_planner_be.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.kits.travel_planner_be.payload.response.ResponseError;
import com.kits.travel_planner_be.payload.response.ResponseSuccess;
import com.kits.travel_planner_be.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/image")
public class StorageController {

    @Autowired
    private StorageService service;

    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        String uploadImage = service.uploadImage(file);

        if (uploadImage != null) {
            Map<String, String> imageUrl = new HashMap<>();
            imageUrl.put("image_url", uploadImage);
            ResponseSuccess<Map<String, String>> responseSuccess = new ResponseSuccess<>();
            responseSuccess.setMessage("Created image successful.");
            responseSuccess.setData(imageUrl);

            return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
        } else {
            Map<String, String> error = new HashMap<>();
            error.put("error_message", "Created image failed");
            ResponseError<Map<String, String>> responseError = new ResponseError<>();
            responseError.setMessage("Bad request.");
            responseError.setError(error);

            return new ResponseEntity<>(responseError, HttpStatus.OK);
        }
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName) {
        byte[] imageData = service.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }
}
