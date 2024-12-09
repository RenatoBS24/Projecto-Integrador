package com.test;

import com.chichos_snack_project.dao.UserDAOImpl;
import com.chichos_snack_project.model.User;
import com.chichos_snack_project.service.UserService;
import com.chichos_snack_project.util.AppConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private DataSource dataSource;

    @Mock
    private Connection connection;

    @Mock
    private UserDAOImpl userDAO;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() throws SQLException {
        when(dataSource.getConnection()).thenReturn(connection);
        when(AppConfig.getDatasources()).thenReturn(dataSource);
    }

    @Test
    public void testValidateWithValidParameters() throws SQLException {
        User user = new User("validUser","passowrd", 1);
        when(userDAO.read(any(User.class))).thenReturn(user);
        assertTrue(userService.validate("validUser", "validPassword"));
    }

    @Test
    public void testValidateWithInvalidUsername() {
        assertFalse(userService.validate("", "validPassword"));
    }

    @Test
    public void testValidateWithInvalidPassword() {
        assertFalse(userService.validate("validUser", ""));
    }

    @Test
    public void testRegisterWithMismatchedPasswords() {
        assertFalse(userService.register("newUser", "password123", "password456", 1));
    }

    @Test
    public void testRegisterWithInvalidRole() {
        assertFalse(userService.register("newUser", "password123", "password123", 0));
    }


    @Test
    public void testUpdateWithMismatchedPasswords() {
        assertFalse(userService.update("existingUser", "newPassword123", "differentPassword"));
    }

    @Test
    public void testUpdateWithInvalidUsername() {
        assertFalse(userService.update("", "newPassword123", "newPassword123"));
    }
}
