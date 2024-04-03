package co.istad.sokcheatmbankingapi.features.media;

import co.istad.sokcheatmbankingapi.exception.MediaException;
import co.istad.sokcheatmbankingapi.features.media.dto.MediaResponse;
import co.istad.sokcheatmbankingapi.util.MediaUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.plaf.UIResource;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MediaServiceImpl implements MediaService {
    @Value("${media.server-path}")
    private String serverPath;
    @Value("${media.base-uri}")
    private String baseUri;

    @Override
    public MediaResponse uploadSingle(MultipartFile file,String folderName) {
        //Generate new unique name for file upload
        String newName = UUID.randomUUID().toString();
        //Extract extension from file upload
        // Assume profile.png
//        int lastDotIndex = file.getOriginalFilename()
//                .lastIndexOf(".");
        String extension = MediaUtil.extractExtension(file.getOriginalFilename());
        log.info("Extension:{} " + extension);
      String  newName1 = newName + "." + extension;
        log.info("NewName: {}"+ newName);
        //copy file to server
        Path path = Paths.get(serverPath+folderName+"\\"+ newName1);
        try{
            Files.copy(file.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
        }catch(IOException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getLocalizedMessage());
        }
        return MediaResponse.builder()
                .name(newName1)
                .contentType(file.getContentType())
                .uri(String.format("%s%s/%s",baseUri,folderName,newName1))
                .extension(extension)
                .size(file.getSize())
                .build();
    }
    @Override
    public List<MediaResponse> uploadMultiple(List<MultipartFile> files, String folderName) {
        //create empty array list, wait for adding uploaded file
        List<MediaResponse> mediaResponses = new ArrayList<>();
        // use loop to upload each files
        files.forEach(file-> {
            MediaResponse mediaResponse = this.uploadSingle(file,folderName);
            mediaResponses.add(mediaResponse);
        });

        return mediaResponses;
    }

    @Override
    public MediaResponse loadFileByName(String mediaName,String folderName) {
        // create absolute path of media
        Path path =  Paths.get(serverPath+folderName+"\\"+mediaName);
        try{
            Resource resource = new UrlResource(path.toUri());
            if(!resource.exists()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Media has not been found");
            }
            return MediaResponse.builder()
                    .name(mediaName)
                    .contentType(Files.probeContentType(path))
                    .uri(String.format("%s%s/%s",baseUri,folderName,mediaName))
                    .extension(MediaUtil.extractExtension(mediaName))
                    .size(resource.contentLength())
                    .build();

        }catch(MalformedURLException e){
            throw  new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getLocalizedMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public MediaResponse deleteMediaByName(String mediaName, String folderName) {
        // create absolute path of media
        Path path = Paths.get(serverPath+folderName+"\\"+mediaName);
        try{
            Long size = Files.size(path);
            if(Files.deleteIfExists(path)){
                return MediaResponse.builder()
                        .name(mediaName)
                        .contentType(Files.probeContentType(path))
                        .uri(String.format("%s%s/%s",baseUri,folderName,mediaName))
                        .extension(MediaUtil.extractExtension(mediaName))
                        .size(size)
                        .build();
            }
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT,
                    "Media has not been found"
            );

        }
        catch(IOException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getLocalizedMessage());
        }
    }

    @Override
    public List<MediaResponse> loadAllMediaFiles(String folderName){
        try {
            List<MediaResponse> getAllMediaFiles = new ArrayList<>();
            Path baseDir = Paths.get(serverPath, folderName);

            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = resolver.getResources("file:" + baseDir.toString() + "/*");

            for (Resource resource : resources) {
                String fileName = resource.getFilename();
                Path filePath = Paths.get(resource.getURI());

                MediaResponse mediaResponse = new MediaResponse(
                        fileName,
                        MediaUtil.extractExtension(fileName),
                        Files.probeContentType(filePath),
                        String.format("%s/%s",baseUri,folderName),
                        Files.size(filePath)
                );
                getAllMediaFiles.add(mediaResponse);
            }

            return getAllMediaFiles;
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error loading media files", e);
        }
    }

    @Override
    public ResponseEntity<Resource> downloadMediaByName(String folderName, String fileName) {
        try {
            Path path = Paths.get(serverPath+folderName+"\\"+fileName);
            Resource resource = new UrlResource(path.toUri());

            if (!resource.exists()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Media file not found");
            }
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", fileName);
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);

        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error downloading media file", e);
        }
    }

}
