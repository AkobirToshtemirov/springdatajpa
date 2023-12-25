package com.pdp.springdatajpa.service;

import com.pdp.springdatajpa.entity.Gender;
import com.pdp.springdatajpa.entity.Group;
import com.pdp.springdatajpa.entity.Student;
import com.pdp.springdatajpa.repository.StudentRepository;
import com.pdp.springdatajpa.repository.StudentRepositoryPaging;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentRepositoryPaging studentRepositoryPaging;

    public StudentService(StudentRepository studentRepository, StudentRepositoryPaging studentRepositoryPaging) {
        this.studentRepository = studentRepository;
        this.studentRepositoryPaging = studentRepositoryPaging;
    }

    public List<Student> getStudentsByBirthDateRange(Date startDate, Date endDate) {
        return studentRepository.findByBirthDateBetween(startDate, endDate);
    }

    public List<Student> getStudentsByGender(Gender gender) {
        return studentRepository.findStudentsByGender(gender);
    }

    public List<Student> getStudentsByGroup(Group group) {
        return studentRepository.findByGroup(group);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student createOrUpdateStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    public Page<Student> getAllStudentsPaged(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return studentRepositoryPaging.findAllWithPageable(pageRequest);
    }
}