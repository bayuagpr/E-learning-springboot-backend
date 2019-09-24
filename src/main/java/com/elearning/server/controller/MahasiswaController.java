package com.elearning.server.controller;

import com.elearning.server.model.Mahasiswa;
import com.elearning.server.service.MahasiswaService;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/v1/mahasiswa")
public class MahasiswaController {
    
  
  private MahasiswaService restService;

    @Autowired
    public MahasiswaController(MahasiswaService restService) {
        this.restService = restService;
    }
    
  @GetMapping("/tampilkan")
  public ResponseEntity<Page< Mahasiswa >> findAll(Pageable paging){
      return ResponseEntity.ok().body(restService.semuaMahasiswa(paging));
  }

  @GetMapping("/pilih")
  public ResponseEntity<Mahasiswa> findOne(@RequestParam("id") String id){
      return ResponseEntity.ok().body(restService.pilihMahasiswa(id));
  }

  @PostMapping
  @ResponseBody
  public ResponseEntity<Mahasiswa> create(@RequestBody Mahasiswa entity ){

      restService.simpanMahasiswa(entity);
      return ResponseEntity.ok().body(entity);
  }

  @PutMapping("/renewal")
  public ResponseEntity<?> updateOne(@RequestParam("id") String id, @Valid @RequestBody  Mahasiswa entity ){
    Mahasiswa r =restService.pilihMahasiswa(id);
    r.setNama(entity.getNama());
    r.setTanggal_lahir(entity.getTanggal_lahir());
    r.setTempat_lahir(entity.getTempat_lahir());
    restService.simpanMahasiswa(r);
      return ResponseEntity.ok().body("Target terbaru pada id "+ id);
  }

  @DeleteMapping("/hapus")
  public ResponseEntity<?> delete(@RequestParam("id") String id){
      restService.hapusMahasiswa(id);
      return ResponseEntity.ok().body("Sukses terhapus.");
  }
}