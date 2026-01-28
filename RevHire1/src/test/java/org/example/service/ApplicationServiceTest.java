package org.example.service;

import org.example.dao.ApplicationDAO;
import org.example.model.Application;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class ApplicationServiceTest {

    @Mock
    private ApplicationDAO applicationDAO;

    @InjectMocks
    private ApplicationService appService;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        Field daoField = ApplicationService.class.getDeclaredField("applicationDAO");
        daoField.setAccessible(true);
        daoField.set(appService, applicationDAO);
    }

    @Test
    void testApplyForJob() {
        when(applicationDAO.applyForJob(1, 1)).thenReturn(true);
        boolean result = appService.applyForJob(1, 1);
        assertTrue(result);
    }

    @Test
    void testGetMyApplications() {
        Application app = new Application(1, 1, 1, "APPLIED", null);
        when(applicationDAO.getApplicationsBySeeker(anyInt())).thenReturn(Arrays.asList(app));

        List<Application> apps = appService.getMyApplications(1);
        assertFalse(apps.isEmpty());
        assertEquals("APPLIED", apps.get(0).getStatus());
    }
}
