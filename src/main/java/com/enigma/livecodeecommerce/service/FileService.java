package com.enigma.livecodeecommerce.service;

import com.enigma.livecodeecommerce.repository.IFileRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
@Transactional
public class FileService {
    @Autowired
    IFileRepository fileRepository;

    public String uploadFile(MultipartFile file) {
        return fileRepository.store(file);
    }

    public Resource downloadFile(String fileName) {
        return fileRepository.load(fileName);
    }
}
