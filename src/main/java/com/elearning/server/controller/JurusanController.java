package com.elearning.server.controller;

import com.elearning.server.model.Jurusan;
import com.elearning.server.model.JurusanList;
import com.elearning.server.service.JurusanService;

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
@RequestMapping("/api/v1/jurusan")
public class JurusanController {
  
  
  private JurusanService restService;

    @Autowired
    public JurusanController(JurusanService restService) {
        this.restService = restService;
    }
    
  @GetMapping("/tampilkan")
  public ResponseEntity<JurusanList> findAll(){
    JurusanList jurusans = new JurusanList();
    jurusans.setJurusanList(restService.semuaJurusan());
      return ResponseEntity.ok().body(jurusans);
  }

  @GetMapping("/pilih")
  public ResponseEntity<Jurusan> findOne(@RequestParam("id") String id){
      return ResponseEntity.ok().body(restService.pilihJurusan(id));
  }

  @PostMapping
  @ResponseBody
  public ResponseEntity<Jurusan> create(@RequestBody Jurusan entity ){
    if(entity.getId()==null){
      UUID uuid = UUID.randomUUID();
      String randomUUIDString = uuid.toString();
      String pre = "JUR";
      String id = pre.concat(randomUUIDString);
      entity.setId(id);
    }
      restService.simpanJurusan(entity);
      return ResponseEntity.ok().body(entity);
  }

  @PutMapping("/renewal")
  public ResponseEntity<?> updateOne(@RequestParam("id") String id, @Valid @RequestBody  Jurusan entity ){
    Jurusan r =restService.pilihJurusan(id);
    r.setNama(entity.getNama());
    restService.simpanJurusan(r);
      return ResponseEntity.ok().body("Target terbaru pada id "+ id);
  }

  @DeleteMapping("/hapus")
  public ResponseEntity<?> delete(@RequestParam("id") String id){
      restService.hapusJurusan(id);
      return ResponseEntity.ok().body("Sukses terhapus.");
  }
}