package com.elearning.server.controller;

import com.elearning.server.model.Kelas;
import com.elearning.server.service.KelasService;

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
@RequestMapping("/api/v1/kelas")
public class KelasController {
  
  
  private KelasService restService;

    @Autowired
    public KelasController(KelasService restService) {
        this.restService = restService;
    }
    
  @GetMapping("/tampilkan")
  public ResponseEntity<Page< Kelas >> findAll(Pageable paging){
      return ResponseEntity.ok().body(restService.semuaKelas(paging));
  }

  @GetMapping("/tampilkanSemua")
  public ResponseEntity<List< Kelas >> findAllLain(){
      return ResponseEntity.ok().body(restService.semuaKelasLain());
  }

  @GetMapping("/pilih")
  public ResponseEntity<Kelas> findOne(@RequestParam("id") String id){
      return ResponseEntity.ok().body(restService.pilihKelas(id));
  }

  @GetMapping("/tampilkan/{nama}")
  public ResponseEntity<Kelas> findKelas(@PathVariable("nama") String nama){
      return ResponseEntity.ok().body(restService.cariKelas(nama));
  }

  @PostMapping
  @ResponseBody
  public ResponseEntity<Kelas> create(@RequestBody Kelas entity ){
    if(entity.getId()==null){
      UUID uuid = UUID.randomUUID();
      String randomUUIDString = uuid.toString();
      String pre = "KEL";
      String id = pre.concat(randomUUIDString);
      entity.setId(id);
    }
      restService.simpanKelas(entity);
      return ResponseEntity.ok().body(entity);
  }

  @PutMapping("/renewal")
  public ResponseEntity<?> updateOne(@RequestParam("id") String id, @Valid @RequestBody  Kelas entity ){
    Kelas r =restService.pilihKelas(id);
    r.setNama(entity.getNama());
    restService.simpanKelas(r);
      return ResponseEntity.ok().body("Target terbaru pada id "+ id);
  }

  @DeleteMapping("/hapus")
  public ResponseEntity<?> delete(@RequestParam("id") String id){
      restService.hapusKelas(id);
      return ResponseEntity.ok().body("Sukses terhapus.");
  }
}