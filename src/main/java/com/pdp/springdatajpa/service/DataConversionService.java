package com.pdp.springdatajpa.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdp.springdatajpa.entity.Group;
import com.pdp.springdatajpa.entity.Student;
import com.pdp.springdatajpa.repository.GroupRepository;
import com.pdp.springdatajpa.repository.StudentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class DataConversionService {

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final ObjectMapper objectMapper;

    public DataConversionService(StudentRepository studentRepository, GroupRepository groupRepository, ObjectMapper objectMapper) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void init() {
        convertAndSaveData();
    }

    public void convertAndSaveData() {
        try {
            File studentsFile = new File("data/students.json");
            Student[] studentsArray = objectMapper.readValue(studentsFile, Student[].class);
            List<Student> students = Arrays.asList(studentsArray);

            File groupsFile = new File("groups.json");
            Group[] groupsArray = objectMapper.readValue(groupsFile, Group[].class);
            List<Group> groups = Arrays.asList(groupsArray);

            groupRepository.saveAll(groups);
            studentRepository.saveAll(students);

            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}