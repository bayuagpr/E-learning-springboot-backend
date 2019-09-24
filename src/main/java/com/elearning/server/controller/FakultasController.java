package com.elearning.server.controller;

import com.elearning.server.model.Fakultas;
import com.elearning.server.service.FakultasService;

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
@RequestMapping("/api/v1/fakultas")
public class FakultasController {
  
  private FakultasService restService;

    @Autowired
    public FakultasController(FakultasService restService) {
        this.restService = restService;
    }
    
  @GetMapping("/tampilkan")
  public ResponseEntity<Page< Fakultas >> findAll(Pageable paging){
      return ResponseEntity.ok().body(restService.semuaFakultas(paging));
  }

  @GetMapping("/pilih")
  public ResponseEntity<Fakultas> findOne(@RequestParam("id") String id){
      return ResponseEntity.ok().body(restService.pilihFakultas(id));
  }

  @PostMapping
  @ResponseBody
  public ResponseEntity<Fakultas> create(@RequestBody Fakultas entity ){
    if(entity.getId()==null){
      UUID uuid = UUID.randomUUID();
      String randomUUIDString = uuid.toString();
      String pre = "FAK";
      String id = pre.concat(randomUUIDString);
      entity.setId(id);
    }
      restService.simpanFakultas(entity);
      return ResponseEntity.ok().body(entity);
  }

  @PutMapping("/renewal")
  public ResponseEntity<?> updateOne(@RequestParam("id") String id, @Valid @RequestBody  Fakultas entity ){
    Fakultas r =restService.pilihFakultas(id);
    r.setNama(entity.getNama());
    r.setDeskripsi(entity.getDeskripsi());
    restService.simpanFakultas(r);
      return ResponseEntity.ok().body("Target terbaru pada id "+ id);
  }

  @DeleteMapping("/hapus")
  public ResponseEntity<?> delete(@RequestParam("id") String id){
      restService.hapusFakultas(id);
      return ResponseEntity.ok().body("Sukses terhapus.");
  }
}