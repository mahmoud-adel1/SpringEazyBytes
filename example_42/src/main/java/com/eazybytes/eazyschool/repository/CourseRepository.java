package com.eazybytes.eazyschool.repository;

import com.eazybytes.eazyschool.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.yaml.snakeyaml.events.Event;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
