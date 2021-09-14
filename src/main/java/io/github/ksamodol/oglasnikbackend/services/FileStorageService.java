package io.github.ksamodol.oglasnikbackend.services;

import io.github.ksamodol.oglasnikbackend.security.User;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileStorageService {
    public Resource loadFileAsResource(String imageName);
    public String storeFiles(MultipartFile[] files, Long listingId);
    public List<String> getImageNames(Long listingId);
    public Resource getListingImage(Long listingId, String imageName);

}
