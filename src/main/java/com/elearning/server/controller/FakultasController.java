package com.elearning.server.controller;

import com.elearning.server.model.Fakultas;
import com.elearning.server.service.FakultasService;

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
@RequestMapping("/api/v1/fakultas")
public class FakultasController {
  
  private FakultasService restService;

    @Autowired
    public FakultasController(FakultasService restService) {
        this.restService = restService;
    }
    
  @GetMapping("/tampilkan")
  public ResponseEntity<List< Fakultas >> findAll(){
      List< Fakultas > entities = restService.semuaFakultas();
      return ResponseEntity.ok().body(entities);
  }

  @PostMapping
  @ResponseBody
  public ResponseEntity<Fakultas> create(@RequestBody Fakultas entity ){

      restService.simpanFakultas(entity);
      return ResponseEntity.ok().body(entity);
  }

  @PutMapping("/renewal/{id}")
  public ResponseEntity<?> updateOne(@PathVariable("id") String id, @Valid @RequestBody  Fakultas entity ){
    Fakultas r =restService.pilihFakultas(id);
    r.setNama(entity.getNama());
    r.setDeskripsi(entity.getDeskripsi());
    restService.simpanFakultas(r);
      return ResponseEntity.ok().body("Target terbaru pada id "+ id);
  }

  // @DeleteMapping("/hapus/{id}")
  // public ResponseEntity<?> delete(@PathVariable("id") String id){
  //     restService.hapusTarget(id);
  //     return ResponseEntity.ok().body("Sukses terhapus.");
  // }
}