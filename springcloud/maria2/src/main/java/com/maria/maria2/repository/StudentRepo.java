package com.maria.maria2.repository;

import com.maria.maria2.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;

public interface StudentRepo extends JpaRepository<Student, Long> {
    List<Student> findByLastName(String surname);
    List<Student> findByLastNameAndFirstNameIsNotLikeAllIgnoreCase(String lastName, String firstName);
    //wyszukaj studentów którzy mają na imię Marian
    @Query("Select s from Student s where s.firstName = 'Marian'")
    List<Student> findStudentsWithNameMarian();
}
//dzięki JpaRepository otrzymujemy dodatkowo możliwość paginacji i sortowania