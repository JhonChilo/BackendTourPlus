package com.dbp.backendtourplus.person.domain;

import com.dbp.backendtourplus.exceptions.ResourceNotFoundException;
import com.dbp.backendtourplus.person.infrastructure.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Optional<Person> findById(Long id) {
        return personRepository.findById(id);
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public Person updatePerson(Long id, String firstname, String lastname, String email) {
        Optional<Person> personOptional = personRepository.findById(id);
        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            person.setFirstname(firstname);
            person.setLastname(lastname);
            person.setEmail(email);
            return personRepository.save(person);
        }
        throw new ResourceNotFoundException("Person not found with id: " + id);
    }

    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }
}