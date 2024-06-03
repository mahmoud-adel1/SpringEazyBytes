package com.eazybytes.eazyschool.service;

import com.eazybytes.eazyschool.model.Course;
import com.eazybytes.eazyschool.model.EazyClass;
import com.eazybytes.eazyschool.model.Person;
import com.eazybytes.eazyschool.repository.CourseRepository;
import com.eazybytes.eazyschool.repository.EazyClassRepository;
import com.eazybytes.eazyschool.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    private EazyClassRepository eazyClassRepository;
    private PersonRepository personRepository;
    private CourseRepository courseRepository;
    @Autowired
    public AdminService(EazyClassRepository eazyClassRepository, PersonRepository personRepository,
                        CourseRepository courseRepository) {
        this.eazyClassRepository = eazyClassRepository;
        this.personRepository = personRepository;
        this.courseRepository = courseRepository;
    }

    public List<EazyClass> getAllClasses() {
        return eazyClassRepository.findAll();
    }

    public EazyClass createNewClass(EazyClass eazyClass) {
        return eazyClassRepository.save(eazyClass);
    }

    public Optional<EazyClass> findClass(int classId) {
        return eazyClassRepository.findById(classId);
    }

    public void deleteClass(EazyClass eazyClass) {
        eazyClassRepository.delete(eazyClass);
    }

    public Person findPersonByEmail(String email) {
        return personRepository.getPersonByEmail(email);
    }

    public void updateClass(EazyClass eazyClass) {
        eazyClassRepository.save(eazyClass);
    }

    public Optional<Person> findPersonById(int personId) {
        return personRepository.findById(personId);
    }

    public void  createCourse(Course course) {
        courseRepository.save(course);
    }

    public Optional<Course> getCourseById(int courseId) {
        return courseRepository.findById(courseId);
    }

    public List<Course> getAllCourses() {
//        return courseRepository.findAllByOrderByNameAsc();
        return courseRepository.findAll(Sort.by("name").descending());
    }

    public Course updateCourse(Course course) {
        return courseRepository.save(course);
    }

    public Person updatePerson(Person person) {
        return personRepository.save(person);
    }
}
