package com.eazybytes.eazyschool.service;

import com.eazybytes.eazyschool.model.Address;
import com.eazybytes.eazyschool.model.Person;
import com.eazybytes.eazyschool.model.Profile;
import com.eazybytes.eazyschool.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    PersonRepository personRepository;

    @Autowired
    public ProfileService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Profile createProfile(Person person) {
        Profile profile = new Profile();
        profile.setName(person.getName());
        profile.setEmail(person.getEmail());
        profile.setMobileNumber(person.getMobileNumber());
        if(person.getAddress() != null && person.getAddress().getAddressId()>0) {
            profile.setAddressOne(person.getAddress().getAddressOne());
            profile.setAddressTwo(person.getAddress().getAddressTwo());
            profile.setCity(person.getAddress().getCity());
            profile.setState(person.getAddress().getState());
            profile.setZipCode(person.getAddress().getZipCode());
        }
        return profile;
    }

    public boolean updatePerson(Profile profile, Person person) {
        boolean isSaved = false;
        person.setName(profile.getName());
        person.setEmail(profile.getEmail());
        person.setMobileNumber(profile.getMobileNumber());
        if(person.getAddress() == null || !(person.getAddress().getAddressId()>0) ) {
            person.setAddress(new Address());
        }
        person.getAddress().setAddressOne(profile.getAddressOne());
        person.getAddress().setAddressTwo(profile.getAddressTwo());
        person.getAddress().setCity(profile.getCity());
        person.getAddress().setState(profile.getState());
        person.getAddress().setZipCode(profile.getZipCode());
        Person updatedPerson = personRepository.save(person);
        if(updatedPerson != null && updatedPerson.getPersonId()>0) {
            isSaved = true;
        }
        return isSaved;
    }
}
