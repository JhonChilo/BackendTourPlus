package com.dbp.backendtourplus.person.application;

import com.dbp.backendtourplus.exceptions.ResourceNotFoundException;
import com.dbp.backendtourplus.person.domain.Person;
import com.dbp.backendtourplus.person.domain.PersonService;
import com.dbp.backendtourplus.person.dto.PersonDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PersonControllerTest {

    private PersonController personController;
    private PersonService personService;

    @BeforeEach
    void setUp() {
        personService = Mockito.mock(PersonService.class);
        personController = new PersonController(personService);
    }

    @Test
    void testGetAllPersons() {
        Person person1 = new Person();
        Person person2 = new Person();
        when(personService.findAll()).thenReturn(Arrays.asList(person1, person2));

        List<Person> result = personController.getAllPersons();

        assertEquals(2, result.size());
        verify(personService, times(1)).findAll();
    }

    @Test
    void testGetPersonById_Found() {
        Long personId = 1L;
        Person person = new Person();
        when(personService.findById(personId)).thenReturn(Optional.of(person));

        ResponseEntity<Person> response = personController.getPersonById(personId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(person, response.getBody());
    }

    @Test
    void testGetPersonById_NotFound() {
        Long personId = 1L;
        when(personService.findById(personId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> personController.getPersonById(personId));
    }

    @Test
    void testCreatePerson() {
        PersonDto personDto = new PersonDto();
        personDto.setFirstname("John");
        personDto.setLastname("Doe");
        Person person = new Person();
        when(personService.save(any(Person.class))).thenReturn(person);

        Person result = personController.createPerson(personDto);

        assertEquals(person, result);
        verify(personService, times(1)).save(any(Person.class));
    }

    @Test
    void testUpdatePerson() {
        Long personId = 1L;
        PersonDto personDto = new PersonDto();
        personDto.setFirstname("Jane");
        personDto.setLastname("Doe");
        Person updatedPerson = new Person();
        when(personService.updatePerson(personId, personDto.getFirstname(), personDto.getLastname(), personDto.getEmail())).thenReturn(updatedPerson);

        ResponseEntity<Person> response = personController.updatePerson(personId, personDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedPerson, response.getBody());
        verify(personService, times(1)).updatePerson(personId, personDto.getFirstname(), personDto.getLastname(), personDto.getEmail());
    }

    @Test
    void testDeletePerson() {
        Long personId = 1L;
        when(personService.findById(personId)).thenReturn(Optional.of(new Person()));
        doNothing().when(personService).deleteById(personId);

        ResponseEntity<Void> response = personController.deletePerson(personId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(personService, times(1)).deleteById(personId);
    }

    @Test
    void testDeletePerson_NotFound() {
        Long personId = 1L;
        when(personService.findById(personId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> personController.deletePerson(personId));
    }
}
