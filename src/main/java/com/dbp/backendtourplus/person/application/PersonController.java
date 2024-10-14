package com.dbp.backendtourplus.person.application;

import com.dbp.backendtourplus.exceptions.ResourceNotFoundException;
import com.dbp.backendtourplus.person.domain.Person;
import com.dbp.backendtourplus.person.domain.PersonService;
import com.dbp.backendtourplus.person.dto.PersonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Person> getAllPersons() {
        return personService.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        return personService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found with id: " + id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Person createPerson(@RequestBody PersonDto personDto) {
        Person person = new Person();
        person.setFirstname(personDto.getFirstname());
        person.setLastname(personDto.getLastname());
        return personService.save(person);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody PersonDto personDto) {
        Person updatedPerson = personService.updatePerson(id, personDto.getFirstname(), personDto.getLastname(), personDto.getEmail());
        return ResponseEntity.ok(updatedPerson);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        if (personService.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Person not found with id: " + id);
        }
        personService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
