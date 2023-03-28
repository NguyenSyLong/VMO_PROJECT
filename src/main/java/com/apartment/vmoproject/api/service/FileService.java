package com.apartment.vmoproject.api.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FileService {
    String saveFile(MultipartFile file);


    String deleteFile(String fileName);
}
