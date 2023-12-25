package com.pdp.springdatajpa.repository;

import com.pdp.springdatajpa.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StudentRepositoryPaging extends PagingAndSortingRepository<Student, Long> {
    @Query(name = "Student.findAllWithPageable")
    Page<Student> findAllWithPageable(Pageable pageable);
}
