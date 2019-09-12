package com.elearning.server.controller;

import com.elearning.server.model.Hasil;
import com.elearning.server.service.HasilService;

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
@RequestMapping("/api/v1/hasil")
public class HasilController {
  
  
  private HasilService restService;

    @Autowired
    public HasilController(HasilService restService) {
        this.restService = restService;
    }
    
  @GetMapping("/tampilkan")
  public ResponseEntity<List< Hasil >> findAll(){
      List< Hasil > entities = restService.semuaHasil();
      return ResponseEntity.ok().body(entities);
  }

  @PostMapping
  @ResponseBody
  public ResponseEntity<Hasil> create(@RequestBody Hasil entity ){

      restService.simpanHasil(entity);
      return ResponseEntity.ok().body(entity);
  }

  @PutMapping("/renewal/{id}")
  public ResponseEntity<?> updateOne(@PathVariable("id") String id, @Valid @RequestBody  Hasil entity ){
    Hasil r =restService.pilihHasil(id);
    r.setAttachment(entity.getAttachment());
    r.setIsiJawaban(entity.getIsiJawaban());
    r.setKomentar(entity.getKomentar());
    r.setNilai(entity.getNilai());
    restService.simpanHasil(r);
      return ResponseEntity.ok().body("Target terbaru pada id "+ id);
  }

  // @DeleteMapping("/hapus/{id}")
  // public ResponseEntity<?> delete(@PathVariable("id") String id){
  //     restService.hapusTarget(id);
  //     return ResponseEntity.ok().body("Sukses terhapus.");
  // }
}