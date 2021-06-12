package io.github.ksamodol.oglasnikbackend.services;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    public String storeFile(MultipartFile image);
    public Resource loadFileAsResource(String imageName);

}
