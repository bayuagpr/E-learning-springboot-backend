package com.elearning.server.controller;

import com.elearning.server.model.Materi;
import com.elearning.server.service.MateriService;

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
@RequestMapping("/api/v1/materi")
public class MateriController {
  
  private MateriService restService;

    @Autowired
    public MateriController(MateriService restService) {
        this.restService = restService;
    }
    
  @GetMapping("/tampilkan")
  public ResponseEntity<List< Materi >> findAll(){
      List< Materi > entities = restService.semuaMateri();
      return ResponseEntity.ok().body(entities);
  }

  @PostMapping
  @ResponseBody
  public ResponseEntity<Materi> create(@RequestBody Materi entity ){

      restService.simpanMateri(entity);
      return ResponseEntity.ok().body(entity);
  }

  @PutMapping("/renewal/{id}")
  public ResponseEntity<?> updateOne(@PathVariable("id") String id, @Valid @RequestBody  Materi entity ){
    Materi r =restService.pilihMateri(id);
    r.setJudul(entity.getJudul());
    r.setAttachment(entity.getAttachment());
    r.setDeskripsi(entity.getDeskripsi());
    restService.simpanMateri(r);
      return ResponseEntity.ok().body("Target terbaru pada id "+ id);
  }

  // @DeleteMapping("/hapus/{id}")
  // public ResponseEntity<?> delete(@PathVariable("id") String id){
  //     restService.hapusTarget(id);
  //     return ResponseEntity.ok().body("Sukses terhapus.");
  // }
}