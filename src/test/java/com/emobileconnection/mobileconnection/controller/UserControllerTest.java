package com.emobileconnection.mobileconnection.controller;

import com.connection.emobile.controller.UserController;
import com.connection.emobile.service.UserServiceImpl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserServiceImpl userService;

    @Test
    public void testAvailableMobileNumbers() {
        ResponseEntity<List<Long>> listOfMobileNumbers = userController.availableMobileNumbers();
        assertNotNull(listOfMobileNumbers);
        assertEquals(200, listOfMobileNumbers.getStatusCodeValue());
    }

}
