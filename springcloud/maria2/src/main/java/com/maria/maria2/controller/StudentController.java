package com.maria.maria2.controller;

import ch.qos.logback.core.util.StringUtil;
import com.maria.maria2.repository.StudentRepo;
import com.maria.maria2.model.Student;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentRepo studentRepo;

    @Autowired
    public StudentController(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }


    @GetMapping
    public List<Student> getStudents() {
        return studentRepo.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student addStudent(@RequestBody Student student) {
        return studentRepo.save(student);
    }
   /* @GetMapping("{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id){
        Optional<Student> studentOptional = studentRepo.findById(id);
        if(studentOptional.isPresent()){
            return ResponseEntity.ok(studentOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }  to ta brzydsza wersja metody*/
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id){
        return studentRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /*@DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id){
        return studentRepo.findById(id)
                .map(student -> {studentRepo.delete(student);
              return   ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    } */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable Long id){
        try {
            studentRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e){

        }

    }
    @PutMapping("/{id}")
    public ResponseEntity<Student> putStudent(@PathVariable Long id, @RequestBody @Validated Student student){
        return studentRepo.findById(id)
                .map(studentFromDb -> {
                    studentFromDb.setFirstName(student.getFirstName());
                    studentFromDb.setLastName(student.getLastName());
                    studentFromDb.setEmail(student.getEmail());
                    return ResponseEntity.ok().body(studentRepo.save(studentFromDb));
                })  .orElseGet(() -> ResponseEntity.notFound().build());


    }
    @PatchMapping("/{id}")
    public ResponseEntity<Student> patchStudent(@PathVariable Long id, @RequestBody @Validated Student student){
        return studentRepo.findById(id)
                .map(studentFromDb -> {
                    if(!StringUtils.isEmpty(student.getFirstName())){
                    studentFromDb.setFirstName(student.getFirstName());}
                    if(!StringUtils.isEmpty(student.getLastName())){
                    studentFromDb.setLastName(student.getLastName());}
                    if(!StringUtils.isEmpty(student.getEmail())){
                    studentFromDb.setEmail(student.getEmail());}
                    return ResponseEntity.ok().body(studentRepo.save(studentFromDb));
                })  .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/lastname")
    public List<Student> findStudent(@RequestParam String lastName){
        return studentRepo.findByLastName(lastName);
    }
    @GetMapping("/find")
    public List<Student> ignoreName(@RequestParam String lastName, @RequestBody String firstName){
        return studentRepo.findByLastNameAndFirstNameIsNotLikeAllIgnoreCase(lastName, firstName);
    }
    @GetMapping("/marian")
    public List<Student> findStudent2(){
        return studentRepo.findStudentsWithNameMarian();
    }

}
