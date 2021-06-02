package com.revature.initiative.controller;

import com.revature.initiative.service.UploadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.*;
        import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class UploadController {

    private UploadServiceImpl amazonS3BucketService;

    @Autowired
    public UploadController(UploadServiceImpl amazonS3BucketService) {
        this.amazonS3BucketService = amazonS3BucketService;
    }

    @PostMapping("/uploadFile")
    public ResponseEntity uploadFile(@RequestPart(value = "file") MultipartFile file) {
        String output = this.amazonS3BucketService.uploadFile(file);
        System.out.println(output);
        return ResponseEntity.ok(output);
    }

    @PostMapping("/deleteFile")
    public String deleteFile(@RequestBody String fileURL) {
        return this.amazonS3BucketService.deleteFileFromBucket(fileURL);
    }
}