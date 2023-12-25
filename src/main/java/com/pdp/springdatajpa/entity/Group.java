package com.pdp.springdatajpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "student_info")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "GROUP_NAME", length = 50, nullable = false)
    private String name;

    @OneToMany(mappedBy = "group")
    private List<Student> students;
}