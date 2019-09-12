package com.elearning.server.controller;

import com.elearning.server.model.Kelas;
import com.elearning.server.service.KelasService;

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
@RequestMapping("/api/v1/kelas")
public class KelasController {
  
  
  private KelasService restService;

    @Autowired
    public KelasController(KelasService restService) {
        this.restService = restService;
    }
    
  @GetMapping("/tampilkan")
  public ResponseEntity<List< Kelas >> findAll(){
      List< Kelas > entities = restService.semuaKelas();
      return ResponseEntity.ok().body(entities);
  }

  @PostMapping
  @ResponseBody
  public ResponseEntity<Kelas> create(@RequestBody Kelas entity ){

      restService.simpanKelas(entity);
      return ResponseEntity.ok().body(entity);
  }

  @PutMapping("/renewal/{id}")
  public ResponseEntity<?> updateOne(@PathVariable("id") String id, @Valid @RequestBody  Kelas entity ){
    Kelas r =restService.pilihKelas(id);
    r.setNama(entity.getNama());
    restService.simpanKelas(r);
      return ResponseEntity.ok().body("Target terbaru pada id "+ id);
  }

  // @DeleteMapping("/hapus/{id}")
  // public ResponseEntity<?> delete(@PathVariable("id") String id){
  //     restService.hapusTarget(id);
  //     return ResponseEntity.ok().body("Sukses terhapus.");
  // }
}