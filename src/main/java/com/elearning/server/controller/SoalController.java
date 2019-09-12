package com.elearning.server.controller;

import com.elearning.server.model.Soal;
import com.elearning.server.service.SoalService;

import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/v1/soal")
public class SoalController {
  
  
  private SoalService restService;

    @Autowired
    public SoalController(SoalService restService) {
        this.restService = restService;
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

  // @DeleteMapping("/hapus/{id}")
  // public ResponseEntity<?> delete(@PathVariable("id") String id){
  //     restService.hapusTarget(id);
  //     return ResponseEntity.ok().body("Sukses terhapus.");
  // }
}