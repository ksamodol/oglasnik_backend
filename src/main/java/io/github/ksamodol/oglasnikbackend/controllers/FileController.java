package io.github.ksamodol.oglasnikbackend.controllers;

import io.github.ksamodol.oglasnikbackend.security.User;
import io.github.ksamodol.oglasnikbackend.services.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RestController
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    private FileStorageService fileStorageService;

    public FileController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }


    @PostMapping("/uploadMultipleFiles")
    public HttpStatus uploadMultipleFiles(@RequestParam("files") MultipartFile[] files, @RequestParam Long listingId, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        fileStorageService.storeFiles(files, listingId);
        return HttpStatus.OK;
    }

    //@GetMapping("/images/{listingId}")
    public List<String> getImageNames(@PathVariable Long listingId){
       return fileStorageService.getImageNames(listingId);
    }
    @GetMapping("/image/{listingId}/{imageName}")
    public ResponseEntity<Resource> getListingImageById(@PathVariable Long listingId, @PathVariable String imageName){ //TODO: sanitize input
        CacheControl cacheControl = CacheControl.maxAge(60, TimeUnit.SECONDS)
                .noTransform()
                .mustRevalidate();
        return ResponseEntity.ok()
                .cacheControl(cacheControl)
                .contentType(MediaType.IMAGE_JPEG)
                .body(fileStorageService.getListingImage(listingId, imageName));
    }

    //@GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


    private static class UploadFileResponse{
        private String fileName;
        private String fileDownloadUri;
        private String fileType;
        private long size;

        public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size) {
            this.fileName = fileName;
            this.fileDownloadUri = fileDownloadUri;
            this.fileType = fileType;
            this.size = size;
        }

        public String getFileName() {
            return fileName;
        }

        public String getFileDownloadUri() {
            return fileDownloadUri;
        }

        public String getFileType() {
            return fileType;
        }

        public long getSize() {
            return size;
        }
    }
}

