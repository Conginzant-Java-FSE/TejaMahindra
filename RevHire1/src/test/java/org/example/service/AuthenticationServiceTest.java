package org.example.service;

import org.example.dao.UserDAO;
import org.example.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;

import org.mindrot.jbcrypt.BCrypt;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AuthenticationServiceTest {

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private AuthenticationService authService;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        Field userDAOField = AuthenticationService.class.getDeclaredField("userDAO");
        userDAOField.setAccessible(true);
        userDAOField.set(authService, userDAO);
    }

    @Test
    void testLogin_Success() {
        String hashedPassword = BCrypt.hashpw("password", BCrypt.gensalt());
        User mockUser = new User("Alice", "alice@test.com", hashedPassword, "SEEKER");
        when(userDAO.getUserByEmail("alice@test.com")).thenReturn(mockUser);

        User result = authService.login("alice@test.com", "password");

        assertNotNull(result);
        assertEquals("Alice", result.getName());
    }

    @Test
    void testLogin_Failure_WrongPassword() {
        String hashedPassword = BCrypt.hashpw("password", BCrypt.gensalt());
        User mockUser = new User("Alice", "alice@test.com", hashedPassword, "SEEKER");
        when(userDAO.getUserByEmail("alice@test.com")).thenReturn(mockUser);

        User result = authService.login("alice@test.com", "wrongpass");

        assertNull(result);
    }

    @Test
    void testLogin_Failure_UserNotFound() {
        when(userDAO.getUserByEmail(anyString())).thenReturn(null);

        User result = authService.login("unknown@test.com", "password");

        assertNull(result);
    }

    @Test
    void testRegisterJobSeeker_Success() {
        when(userDAO.getUserByEmail("new@test.com")).thenReturn(null);
        when(userDAO.registerJobSeeker(any())).thenReturn(true);

        boolean result = authService.registerJobSeeker("Bob", "new@test.com", "pass", "12345");

        assertTrue(result);
        verify(userDAO).registerJobSeeker(any());
    }

    @Test
    void testRegisterJobSeeker_EmailExists() {
        when(userDAO.getUserByEmail("existing@test.com")).thenReturn(new User());

        boolean result = authService.registerJobSeeker("Bob", "existing@test.com", "pass", "12345");

        assertFalse(result);
        verify(userDAO, never()).registerJobSeeker(any());
    }
}
