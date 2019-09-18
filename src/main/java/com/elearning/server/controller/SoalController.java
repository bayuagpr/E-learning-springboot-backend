package com.elearning.server.controller;

import com.elearning.server.model.Materi;
import com.elearning.server.model.Soal;
import com.elearning.server.payload.UploadFileResponse;
import com.elearning.server.service.FileStorageService;
import com.elearning.server.service.SoalService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/v1/soal")
public class SoalController {
  
  
  private SoalService restService;
  private FileStorageService fileStorageService;

  @Autowired
  public SoalController(SoalService restService, FileStorageService fileStorageService) {
      this.restService = restService;
      this.fileStorageService = fileStorageService;
  }
    
  @GetMapping("/tampilkan")
  public ResponseEntity<List< Soal >> findAll(){
      List< Soal > entities = restService.semuaSoal();
      return ResponseEntity.ok().body(entities);
  }

  @PostMapping
  @ResponseBody
  public ResponseEntity<Soal> create(@RequestBody Soal entity ){

      restService.simpanSoal(entity);
      return ResponseEntity.ok().body(entity);
  }

  @PutMapping("/renewal/{id}")
  public ResponseEntity<?> updateOne(@PathVariable("id") String id, @Valid @RequestBody  Soal entity ){
    Soal r =restService.pilihSoal(id);
    r.setJudul(entity.getJudul());
    r.setAttachment(entity.getAttachment());
    r.setDeskripsi(entity.getDeskripsi());
    r.setDueDate(entity.getDueDate());
    r.setTipe(entity.getTipe());
    restService.simpanSoal(r);
      return ResponseEntity.ok().body("Target terbaru pada id "+ id);
  }

  @PostMapping("/uploadFile/{id}")
  public  UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file,@PathVariable("id") String id) {
      String fileName = fileStorageService.storeFile(file);

      String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
              .path("/api/v1/materi/downloadFile/")
              .path(fileName)
              .toUriString();
              
              Materi r =restService.pilihMateri(id);
              r.setAttachment(fileDownloadUri);
              


      return new UploadFileResponse(fileName, fileDownloadUri,
              file.getContentType(), file.getSize());
  }

  // @PostMapping("/uploadMultipleFiles")
  // public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
  //     return Arrays.asList(files)
  //             .stream()
  //             .map(file -> uploadFile(file))
  //             .collect(Collectors.toList());
  // }

  @GetMapping("/downloadFile/{fileName:.+}")
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

  // @DeleteMapping("/hapus/{id}")
  // public ResponseEntity<?> delete(@PathVariable("id") String id){
  //     restService.hapusTarget(id);
  //     return ResponseEntity.ok().body("Sukses terhapus.");
  // }
}