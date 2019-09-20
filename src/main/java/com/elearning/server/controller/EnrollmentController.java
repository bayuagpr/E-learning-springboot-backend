package com.elearning.server.controller;

import com.elearning.server.model.Enrollment;
import com.elearning.server.service.EnrollmentService;

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
@RequestMapping("/api/v1/Enrollment")
public class EnrollmentController {
  
  private EnrollmentService restService;

    @Autowired
    public EnrollmentController(EnrollmentService restService) {
        this.restService = restService;
    }
    
  @GetMapping("/tampilkan")
  public ResponseEntity<List< Enrollment >> findAll(){
      List< Enrollment > entities = restService.semuaEnrollment();
      return ResponseEntity.ok().body(entities);
  }

  @GetMapping("/tampilkan/{id}")
  public ResponseEntity<Enrollment> findOne(@PathVariable("id") String id){
      return ResponseEntity.ok().body(restService.pilihEnrollment(id));
  }

  @PostMapping
  @ResponseBody
  public ResponseEntity<Enrollment> create(@RequestBody Enrollment entity ){
    if(entity.getId()==null){
      UUID uuid = UUID.randomUUID();
      String randomUUIDString = uuid.toString();
      String pre = "ENR";
      String id = pre.concat(randomUUIDString);
      entity.setId(id);
    }
      restService.simpanEnrollment(entity);
      return ResponseEntity.ok().body(entity);
  }

  @PutMapping("/renewal/{id}")
  public ResponseEntity<?> updateOne(@PathVariable("id") String id, @Valid @RequestBody  Enrollment entity ){
    Enrollment r =restService.pilihEnrollment(id);
    r.setDisetujui(entity.getDisetujui());
    restService.simpanEnrollment(r);
      return ResponseEntity.ok().body("Target terbaru pada id "+ id);
  }

  // @DeleteMapping("/hapus/{id}")
  // public ResponseEntity<?> delete(@PathVariable("id") String id){
  //     restService.hapusTarget(id);
  //     return ResponseEntity.ok().body("Sukses terhapus.");
  // }
}