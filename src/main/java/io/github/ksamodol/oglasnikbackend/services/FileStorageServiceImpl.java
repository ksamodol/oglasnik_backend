package io.github.ksamodol.oglasnikbackend.services;

import io.github.ksamodol.oglasnikbackend.configuration.FileStorageConfig;
import io.github.ksamodol.oglasnikbackend.entity.listing.ListingDTO;
import io.github.ksamodol.oglasnikbackend.exception.FileStorageException;
import io.github.ksamodol.oglasnikbackend.exception.MyFileNotFoundException;
import io.github.ksamodol.oglasnikbackend.security.User;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final Path fileStorageLocation;
    private ListingService listingService;


    public FileStorageServiceImpl(FileStorageConfig fileStorageConfig, ListingService listingService) {
        this.fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir())
                .toAbsolutePath().normalize();
        this.listingService = listingService;

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @Override
    public String storeFiles(MultipartFile[] files, Long listingId, User user) {
        Optional<ListingDTO> listing = listingService.findListingById(listingId);

        if(listing.isEmpty()){
            throw new IllegalArgumentException("Bad listing id!");
        }
        if(!listing.get().getUserUsername().equals(user.getUsername())){
            throw new AccessDeniedException("Access denied!");
        }


        for(int i = 0; i < files.length; i++) {
            String contentType = files[i].getContentType();
            if (!"image/jpeg".equals(contentType) && !"image/png".equals(contentType)) {
                throw new IllegalArgumentException("Only image type files are supported!");
            }
            Path targetLocation = this.fileStorageLocation
                    .resolve(String.valueOf(listingId))
                    .resolve(String.valueOf(i) + "." + FilenameUtils.getExtension(files[i].getOriginalFilename()));
            try {
                Files.createDirectories(targetLocation.getParent());
                Files.copy(files[i].getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new FileStorageException("Could not store file. Please try again!", e);
            }
        }
        return "alles gut";
    }

    @Override
    public List<String> getListingFiles(Long listingId) {
        List<String> fileNames = new ArrayList<String>();
        Path targetLocation = this.fileStorageLocation.resolve(String.valueOf(listingId));
        File listingDirectory = targetLocation.toFile();

        if(!listingDirectory.isDirectory()){
            return fileNames;
        }

        File[] files = listingDirectory.listFiles();

        if(files == null){
            return fileNames;
        }

        for(File file: files){
            fileNames.add(file.getName());
        }

        return fileNames;
    }

    @Override
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }
}
