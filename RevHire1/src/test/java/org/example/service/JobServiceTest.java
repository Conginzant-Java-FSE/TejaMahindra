package org.example.service;

import org.example.dao.JobDAO;
import org.example.model.Job;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JobServiceTest {

    @Mock
    private JobDAO jobDAO;

    @InjectMocks
    private JobService jobService;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        Field jobDAOField = JobService.class.getDeclaredField("jobDAO");
        jobDAOField.setAccessible(true);
        jobDAOField.set(jobService, jobDAO);
    }

    @Test
    void testPostJob() {
        when(jobDAO.postJob(any(Job.class))).thenReturn(true);
        boolean result = jobService.postJob(1, "Dev", "Write code", "Java", "Remote", "100k", "Full-time",
                "Entry Level");
        assertTrue(result);
        verify(jobDAO).postJob(any(Job.class));
    }

    @Test
    void testGetAllJobs() {
        Job job = new Job(1, 1, "Dev", "Desc", "Req", "Loc", "Sal", "Type", "Entry Level", null);
        when(jobDAO.getAllJobs()).thenReturn(Arrays.asList(job));

        List<Job> jobs = jobService.getAllJobs();
        assertFalse(jobs.isEmpty());
        assertEquals(1, jobs.size());
    }

    @Test
    void testSearchJobs() {
        Job job = new Job(1, 1, "Dev", "Desc", "Req", "Loc", "Sal", "Type", "Entry Level", null);
        when(jobDAO.searchJobs("title", "Dev")).thenReturn(Arrays.asList(job));

        List<Job> jobs = jobService.searchJobs("title", "Dev");
        assertEquals(1, jobs.size());
        assertEquals("Dev", jobs.get(0).getTitle());
    }
}
