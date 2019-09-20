package com.elearning.server.controller;

import com.elearning.server.model.MataKuliah;
import com.elearning.server.service.MataKuliahService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
@RequestMapping("/api/v1/matakuliah")
public class MataKuliahController {
  
  
  private MataKuliahService restService;

    @Autowired
    public MataKuliahController(MataKuliahService restService) {
        this.restService = restService;
    }
    
  @GetMapping("/tampilkan")
  public ResponseEntity<List< MataKuliah >> findAll(){
      List< MataKuliah > entities = restService.semuaMataKuliah();
      return ResponseEntity.ok().body(entities);
  }

  @GetMapping("/tampilkan/{id}")
  public ResponseEntity<MataKuliah> findOne(@PathVariable("id") String id){
      return ResponseEntity.ok().body(restService.pilihMataKuliah(id));
  }

  @PostMapping
  @ResponseBody
  public ResponseEntity<MataKuliah> create(@RequestBody MataKuliah entity ){
    if(entity.getId()==null){
      UUID uuid = UUID.randomUUID();
      String randomUUIDString = uuid.toString();
      String pre = "MK";
      String id = pre.concat(randomUUIDString);
      entity.setId(id);
    }
      restService.simpanMataKuliah(entity);
      return ResponseEntity.ok().body(entity);
  }

  @PutMapping("/renewal/{id}")
  public ResponseEntity<?> updateOne(@PathVariable("id") String id, @Valid @RequestBody  MataKuliah entity ){
    MataKuliah r =restService.pilihMataKuliah(id);
    r.setNama(entity.getNama());
    r.setTerm(entity.getTerm());
    r.setTipe(entity.getTipe());
    restService.simpanMataKuliah(r);
      return ResponseEntity.ok().body("Target terbaru pada id "+ id);
  }

  // @DeleteMapping("/hapus/{id}")
  // public ResponseEntity<?> delete(@PathVariable("id") String id){
  //     restService.hapusTarget(id);
  //     return ResponseEntity.ok().body("Sukses terhapus.");
  // }
}