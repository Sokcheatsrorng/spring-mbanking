package co.istad.sokcheatmbankingapi.features.media;

import co.istad.sokcheatmbankingapi.features.media.dto.MediaResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/v1/medias")
@RequiredArgsConstructor
public class MediaController {
    private final MediaService mediaService;
    @PostMapping("/upload-single")
    @ResponseStatus(HttpStatus.CREATED)
    MediaResponse uploadSingle(@Valid @RequestPart MultipartFile file){
        return mediaService.uploadSingle(file,"IMAGE");
    }
    @PostMapping("/upload-multiple")
    @ResponseStatus(HttpStatus.CREATED)
    List<MediaResponse> uploadMultiple(@Valid @RequestPart List<MultipartFile> files){
        return mediaService.uploadMultiple(files,"IMAGE");
    }
    @GetMapping("/{mediaName}")
    public MediaResponse loadFileByName(@PathVariable String mediaName){
        return mediaService.loadFileByName(mediaName,"IMAGE");
    }
    @DeleteMapping("/{mediaName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFileByName(@PathVariable String mediaName){
        mediaService.loadFileByName(mediaName,"IMAGE");
    }

    @GetMapping("/load-all-files")
    public List<MediaResponse> loadAllMediaFiles() {
        return mediaService.loadAllMediaFiles("IMAGE");
    }
    @GetMapping(path={"/{mediaName}/download","/download/{mediaName}"},
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public ResponseEntity<?> downloadMediaByName( @PathVariable String mediaName) {
        Resource resources = mediaService.downloadMediaByName(mediaName,"IMAGE");
        HttpHeaders headers = new HttpHeaders();
//        headers.set(HttpHeaders.CONTENT_DISPOSITION("attachment:","fileName:"+mediaName);
        headers.setContentDispositionFormData("attachment","fileName: "+ mediaName);
        return ResponseEntity.ok()
                .headers(headers)
                .body(resources);
    }
}


