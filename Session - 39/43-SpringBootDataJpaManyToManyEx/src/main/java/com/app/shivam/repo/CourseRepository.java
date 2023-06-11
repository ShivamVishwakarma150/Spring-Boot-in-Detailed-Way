package com.app.shivam.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.shivam.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

}
