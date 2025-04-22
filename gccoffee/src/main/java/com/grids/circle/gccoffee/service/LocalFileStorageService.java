package com.grids.circle.gccoffee.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class LocalFileStorageService implements FileStorageService {

    private final String uploadDir = "src/main/resources/static/images"; // 저장 위치

    @Override
    public String store(MultipartFile file) {
        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        File dest = new File(uploadDir, filename);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw new RuntimeException("이미지 저장 실패", e);
        }
        return "/images/" + filename; // URL로 사용할 경로 반환
    }
}