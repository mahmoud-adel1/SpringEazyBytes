package com.eazybytes.eazyschool.service;

import com.eazybytes.eazyschool.model.EazyClass;
import com.eazybytes.eazyschool.model.Person;
import com.eazybytes.eazyschool.repository.EazyClassRepository;
import com.eazybytes.eazyschool.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    private EazyClassRepository eazyClassRepository;
    private PersonRepository personRepository;

    @Autowired
    public AdminService(EazyClassRepository eazyClassRepository, PersonRepository personRepository) {
        this.eazyClassRepository = eazyClassRepository;
        this.personRepository = personRepository;
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
}
