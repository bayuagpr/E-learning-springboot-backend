package com.elearning.server.controller;

import com.elearning.server.model.Dosen;
import com.elearning.server.service.DosenService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
@RequestMapping("/api/v1/dosen")
public class DosenController {
  
  private DosenService restService;

    @Autowired
    public DosenController(DosenService restService) {
        this.restService = restService;
    }

  @GetMapping("/tampilkan")
  public ResponseEntity<Page< Dosen >> findAll(Pageable paging){
      return ResponseEntity.ok().body(restService.semuaDosen(paging));
  }

  @PostMapping
  @ResponseBody
  public ResponseEntity<Dosen> create(@RequestBody Dosen entity ){

      restService.simpanDosen(entity);
      return ResponseEntity.ok().body(entity);
  }

  @PutMapping("/renewal")
  public ResponseEntity<?> updateOne(@RequestParam("id") String id, @Valid @RequestBody  Dosen entity ){
    Dosen r =restService.pilihDosen(id);
    r.setNama(entity.getNama());
    r.setAlamat(entity.getAlamat());
    r.setTanggal_lahir(entity.getTanggal_lahir());
    r.setTempat_lahir(entity.getTempat_lahir());
    restService.simpanDosen(r);
      return ResponseEntity.ok().body("Target terbaru pada id "+ id);
  }

  @GetMapping("/pilih")
  public ResponseEntity<Dosen> findOne(@RequestParam("id") String id){
      return ResponseEntity.ok().body(restService.pilihDosen(id));
  }

  @DeleteMapping("/hapus")
  public ResponseEntity<?> delete(@RequestParam("id") String id){
      restService.hapusDosen(id);
      return ResponseEntity.ok().body("Sukses terhapus.");
  }
}