package com.eazybytes.eazyschool.service;

import com.eazybytes.eazyschool.constants.EazySchoolConstants;
import com.eazybytes.eazyschool.model.Person;
import com.eazybytes.eazyschool.model.Role;
import com.eazybytes.eazyschool.repository.PersonRepository;
import com.eazybytes.eazyschool.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    PersonRepository personRepository;
    RoleRepository roleRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, RoleRepository roleRepository) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
    }

    public boolean createNewPerson(Person person) {
        Role role = roleRepository.findRoleByRoleName(EazySchoolConstants.STUDENT_ROLE);
        person.setRole(role);
        Person savedPerson = personRepository.save(person);
        if(savedPerson != null && savedPerson.getPersonId()>0) {
            return true;
        }
        return false;
    }
}
