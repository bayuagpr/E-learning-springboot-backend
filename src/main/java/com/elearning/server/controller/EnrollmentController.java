package com.elearning.server.controller;

import com.elearning.server.model.Enrollment;
import com.elearning.server.model.ExistEnroll;
import com.elearning.server.service.EnrollmentService;

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
@RequestMapping("/api/v1/enrollment")
public class EnrollmentController {
  
  private EnrollmentService restService;

    @Autowired
    public EnrollmentController(EnrollmentService restService) {
        this.restService = restService;
    }
    
  @GetMapping("/tampilkan")
  public ResponseEntity<Page< Enrollment >> findAll(Pageable paging){
      return ResponseEntity.ok().body(restService.semuaEnrollment(paging));
  }

  @GetMapping("/tampilkanSemuaKelas")
  public ResponseEntity<Page< Enrollment >> findAllByKelas(@RequestParam("id") String id,@RequestParam("disetujui") Boolean disetujui, Pageable paging){
      return ResponseEntity.ok().body(restService.semuaHasilKelas(id,disetujui, paging));
  }

  @GetMapping("/tampilkanSemuaMahasiswa")
  public ResponseEntity<Page< Enrollment >> findAllByMahasiswa(@RequestParam("id") String id,@RequestParam("disetujui") Boolean disetujui, Pageable paging){
      return ResponseEntity.ok().body(restService.semuaHasilMahasiswa(id,disetujui,paging));
  }

  @GetMapping("/pilih")
  public ResponseEntity<Enrollment> findOne(@RequestParam("id") String id){
      return ResponseEntity.ok().body(restService.pilihEnrollment(id));
  }

  @GetMapping("/existEnrollment")
  public ResponseEntity<ExistEnroll> existEnrollment(@RequestParam("idMahasiswa") String idMahasiswa,@RequestParam("idKelas") String idKelas){
    ExistEnroll e =  new ExistEnroll();  
    if(restService.existMahasiswaKelas(idMahasiswa, idKelas)){
        List<Enrollment> enroll =restService.findMahasiswaKelas(idMahasiswa, idKelas);
        e.setEnrollmentList(enroll);
        e.setStatus(true);
        return ResponseEntity.ok().body(e);
      }else{
        e.setStatus(false);
        return ResponseEntity.ok().body(e);
      }
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

  @PutMapping("/renewal")
  public ResponseEntity<?> updateOne(@RequestParam("id") String id, @Valid @RequestBody  Enrollment entity ){
    Enrollment r =restService.pilihEnrollment(id);
    r.setDisetujui(entity.getDisetujui());
    restService.simpanEnrollment(r);
      return ResponseEntity.ok().body("Target terbaru pada id "+ id);
  }
  
  @DeleteMapping("/hapus")
  public ResponseEntity<?> delete(@RequestParam("id") String id){
      restService.hapusEnrollment(id);
      return ResponseEntity.ok().body("Sukses terhapus.");
  }
}