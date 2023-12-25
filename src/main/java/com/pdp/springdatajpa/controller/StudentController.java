package com.pdp.springdatajpa.controller;

import com.pdp.springdatajpa.entity.Gender;
import com.pdp.springdatajpa.entity.Group;
import com.pdp.springdatajpa.entity.Student;
import com.pdp.springdatajpa.service.GroupService;
import com.pdp.springdatajpa.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final GroupService groupService;

    @Autowired
    public StudentController(StudentService studentService, GroupService groupService) {
        this.studentService = studentService;
        this.groupService = groupService;
    }

    @GetMapping("/birthDateRange")
    public ResponseEntity<List<Student>> getStudentsByBirthDateRange(@RequestParam("startDate") Date startDate,
                                                                     @RequestParam("endDate") Date endDate) {
        List<Student> students = studentService.getStudentsByBirthDateRange(startDate, endDate);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/gender/{gender}")
    public ResponseEntity<List<Student>> getStudentsByGender(@PathVariable Gender gender) {
        List<Student> students = studentService.getStudentsByGender(gender);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Optional<Student> student = studentService.getStudentById(id);
        return student.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.createOrUpdateStudent(student);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        student.setId(id); // Ensure the ID matches the path variable
        Student updatedStudent = studentService.createOrUpdateStudent(student);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<Student>> getStudentsByGroup(@PathVariable Long groupId) {
        Group group = groupService.getGroupById(groupId);
        List<Student> studentsByGroup = studentService.getStudentsByGroup(group);
        return new ResponseEntity<>(studentsByGroup, HttpStatus.OK);
    }

    @GetMapping("/students")
    public ResponseEntity<Page<Student>> getStudentsPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Student> studentsPage = studentService.getAllStudentsPaged(page, size);
        return ResponseEntity.ok(studentsPage);
    }
}
