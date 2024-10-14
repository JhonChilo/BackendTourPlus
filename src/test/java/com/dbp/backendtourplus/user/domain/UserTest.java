package com.dbp.backendtourplus.user.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("securepassword");
        user.setRole(Role.USER);
    }

    @Test
    void testUserDetailsAttributes() {
        assertEquals("John", user.getFirstname(), "El nombre del usuario debería ser 'John'");
        assertEquals("Doe", user.getLastname(), "El apellido del usuario debería ser 'Doe'");
        assertEquals("john.doe@example.com", user.getEmail(), "El correo del usuario debería ser 'john.doe@example.com'");
        assertEquals("securepassword", user.getPassword(), "La contraseña del usuario debería ser 'securepassword'");
        assertEquals(Role.USER, user.getRole(), "El rol del usuario debería ser 'USER'");
    }

    @Test
    void testUserAuthorities() {
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        assertNotNull(authorities, "Las autoridades no deberían ser nulas");
        assertEquals(1, authorities.size(), "El usuario debería tener una autoridad");

        GrantedAuthority authority = authorities.iterator().next();
        assertEquals("ROLE_USER", authority.getAuthority(), "El rol del usuario debería ser 'ROLE_USER'");
    }

    @Test
    void testUsernameIsEmail() {
        assertEquals("john.doe@example.com", user.getUsername(), "El nombre de usuario debería ser el correo electrónico");
    }

    @Test
    void testAccountStatus() {
        assertTrue(user.isAccountNonExpired(), "La cuenta del usuario no debería estar expirada");
        assertTrue(user.isAccountNonLocked(), "La cuenta del usuario no debería estar bloqueada");
        assertTrue(user.isCredentialsNonExpired(), "Las credenciales del usuario no deberían estar expiradas");
        assertTrue(user.isEnabled(), "El usuario debería estar habilitado");
    }
}
