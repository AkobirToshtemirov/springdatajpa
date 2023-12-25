package com.pdp.springdatajpa.repository;

import com.pdp.springdatajpa.entity.Gender;
import com.pdp.springdatajpa.entity.Group;
import com.pdp.springdatajpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByBirthDateBetween(Date startDate, Date endDate);

    @Query("SELECT s FROM Student s WHERE s.gender = :gender")
    List<Student> findStudentsByGender(@Param("gender") Gender gender);

    List<Student> findByGroup(Group group);
}