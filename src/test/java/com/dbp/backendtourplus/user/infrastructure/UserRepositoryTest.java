package com.dbp.backendtourplus.user.infrastructure;

import com.dbp.backendtourplus.user.domain.User;
import com.dbp.backendtourplus.user.domain.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setEmail("johndoe@example.com");
        user.setPassword("SecurePassword123");
        user.setRole(Role.USER);

        entityManager.persistAndFlush(user);
    }

    @Test
    public void testCreateUser() {
        User newUser = new User();
        newUser.setFirstname("Jane");
        newUser.setLastname("Doe");
        newUser.setEmail("janedoe@example.com");
        newUser.setPassword("AnotherSecurePassword");
        newUser.setRole(Role.ADMIN);

        User savedUser = userRepository.saveAndFlush(newUser);

        Optional<User> createdUser = userRepository.findById(savedUser.getId());
        assertTrue(createdUser.isPresent());
        assertEquals(newUser.getEmail(), createdUser.get().getEmail());
    }

    @Test
    public void testFindById() {
        Optional<User> optionalUser = userRepository.findById(user.getId());
        assertTrue(optionalUser.isPresent(), "El usuario no se encontró");

        User foundUserById = optionalUser.get();
        assertEquals(user.getId(), foundUserById.getId());
    }

    @Test
    public void testDeleteById() {
        userRepository.deleteById(user.getId());
        entityManager.flush();

        Optional<User> optionalDeletedUser = userRepository.findById(user.getId());
        assertFalse(optionalDeletedUser.isPresent(), "El usuario no se eliminó correctamente");
    }
}