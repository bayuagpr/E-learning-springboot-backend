package com.elearning.server.controller;

import com.elearning.server.model.Hasil;
import com.elearning.server.model.StatusHasil;
import com.elearning.server.payload.UploadFileResponse;
import com.elearning.server.service.HasilService;
import com.elearning.server.service.HasilStorageService;
import com.elearning.server.service.SoalService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/v1/hasil")
public class HasilController {
  
  
  private HasilService restService;
  private HasilStorageService fileStorageService;

    @Autowired
    public HasilController(HasilService restService, HasilStorageService fileStorageService) {
        this.restService = restService;
        this.fileStorageService = fileStorageService;
    }
    
  @GetMapping("/tampilkan")
  public ResponseEntity<Page< Hasil >> findAll(Pageable paging){
      return ResponseEntity.ok().body(restService.semuaHasil(paging));
  }

  @GetMapping("/pilih")
  public ResponseEntity<Hasil> findOne(@RequestParam("id") String id){
      return ResponseEntity.ok().body(restService.pilihHasil(id));
  }

  @PostMapping
  @ResponseBody
  public ResponseEntity<Hasil> create(@RequestBody Hasil entity ){
    Date tanggalSekarang = new Date();
    if(entity.getId()==null){
      UUID uuid = UUID.randomUUID();
      String randomUUIDString = uuid.toString();
      String pre = "HAS";
      String id = pre.concat(randomUUIDString);
      entity.setId(id);
    }
      if(tanggalSekarang.before(entity.getSoal().getDueDate()) || tanggalSekarang.compareTo(entity.getSoal().getDueDate()) == 0){
        entity.setStatus(StatusHasil.Early);
      } else {
        entity.setStatus(StatusHasil.Late);
      }
      entity.setLastModified(tanggalSekarang);
      restService.simpanHasil(entity);
      return ResponseEntity.ok().body(entity);
  }

  @PutMapping("/renewal")
  public ResponseEntity<?> updateOne(@RequestParam("id") String id, @Valid @RequestBody  Hasil entity ){
    Date tanggalSekarang = new Date();
    Hasil r =restService.pilihHasil(id);
    r.setAttachment(entity.getAttachment());
    r.setIsiJawaban(entity.getIsiJawaban());
    r.setKomentar(entity.getKomentar());
    r.setNilai(entity.getNilai());
    r.setLastModified(tanggalSekarang);
    restService.simpanHasil(r);
      return ResponseEntity.ok().body("Target terbaru pada id "+ id);
  }

   @PostMapping("/uploadHasil")
  public  UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
      String fileName = fileStorageService.storeFile(file);

      String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
              .path("/api/v1/materi/downloadSoal/")
              .path(fileName)
              .toUriString();
              


      return new UploadFileResponse(fileName, fileDownloadUri,
              file.getContentType(), file.getSize());
  }

  @PostMapping("/uploadMultipleHasil")
  public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
      return Arrays.asList(files)
              .stream()
              .map(file -> uploadFile(file))
              .collect(Collectors.toList());
  }

  @GetMapping("/downloadHasil/{fileName:.+}")
  public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
      // Load file as Resource
      Resource resource = fileStorageService.loadFileAsResource(fileName);

      // Try to determine file's content type
      String contentType = null;
      try {
          contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
      } catch (IOException ex) {
          log.info("Could not determine file type.");
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
  
  @DeleteMapping("/hapus")
  public ResponseEntity<?> delete(@RequestParam("id") String id){
      restService.hapusHasil(id);
      return ResponseEntity.ok().body("Sukses terhapus.");
  }
}