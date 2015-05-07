package com.kb.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.kb.Application;
import com.kb.domain.AssignmentType;
import com.kb.domain.SystemAnnouncement;
import com.kb.repository.SystemAnnouncementRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class SystemAnnouncementResourceTest {

    private static final AssignmentType DEFAULT_ASSIGNMENT_TYPE = AssignmentType.ALL;
    private static final AssignmentType UPDATED_ASSIGNMENT_TYPE = AssignmentType.CUSTOM;
    private static final String DEFAULT_SUBJECT = "SAMPLE_TEXT";
    private static final String UPDATED_SUBJECT = "UPDATED_TEXT";
    private static final String DEFAULT_MESSAGE = "SAMPLE_TEXT";
    private static final String UPDATED_MESSAGE = "UPDATED_TEXT";

    @Inject
    private SystemAnnouncementRepository repository;

    private MockMvc restSystemAnnouncementMockMvc;

    private SystemAnnouncement systemAnnouncement;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        SystemAnnouncementResource systemAnnouncementResource = new SystemAnnouncementResource();
        ReflectionTestUtils.setField(systemAnnouncementResource, "repository", repository);
        this.restSystemAnnouncementMockMvc = MockMvcBuilders.standaloneSetup(systemAnnouncementResource).build();
    }

    @Before
    public void initTest() {
        systemAnnouncement = new SystemAnnouncement();
        systemAnnouncement.setAssignmentType(DEFAULT_ASSIGNMENT_TYPE);
        systemAnnouncement.setSubject(DEFAULT_SUBJECT);
        systemAnnouncement.setMessage(DEFAULT_MESSAGE);
    }

    @Test
    @Transactional
    public void createSystemAnnouncement() throws Exception {
        int databaseSizeBeforeCreate = repository.findAll().size();

        // Create the SystemAnnouncement
        restSystemAnnouncementMockMvc.perform(post("/api/messages")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(systemAnnouncement)))
                .andExpect(status().isCreated());

        // Validate the SystemAnnouncement in the database
        List<SystemAnnouncement> systemAnnouncements = repository.findAll();
        assertThat(systemAnnouncements).hasSize(databaseSizeBeforeCreate + 1);
        SystemAnnouncement testSystemAnnouncement = systemAnnouncements.get(systemAnnouncements.size() - 1);
        assertThat(testSystemAnnouncement.getAssignmentType()).isEqualTo(DEFAULT_ASSIGNMENT_TYPE);
        assertThat(testSystemAnnouncement.getSubject()).isEqualTo(DEFAULT_SUBJECT);
        assertThat(testSystemAnnouncement.getMessage()).isEqualTo(DEFAULT_MESSAGE);
    }

    @Test
    @Transactional
    public void getAllSystemAnnouncements() throws Exception {
        // Initialize the database
        repository.saveAndFlush(systemAnnouncement);

        // Get all the systemAnnouncements
        restSystemAnnouncementMockMvc.perform(get("/api/messages"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(systemAnnouncement.getId().intValue())))
                .andExpect(jsonPath("$.[*].assignmentType").value(hasItem(DEFAULT_ASSIGNMENT_TYPE.toString())))
                .andExpect(jsonPath("$.[*].subject").value(hasItem(DEFAULT_SUBJECT.toString())))
                .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE.toString())));
    }

    @Test
    @Transactional
    public void getSystemAnnouncement() throws Exception {
        // Initialize the database
        repository.saveAndFlush(systemAnnouncement);

        // Get the systemAnnouncement
        restSystemAnnouncementMockMvc.perform(get("/api/messages/{id}", systemAnnouncement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(systemAnnouncement.getId().intValue()))
            .andExpect(jsonPath("$.assignmentType").value(DEFAULT_ASSIGNMENT_TYPE.toString()))
            .andExpect(jsonPath("$.subject").value(DEFAULT_SUBJECT.toString()))
            .andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSystemAnnouncement() throws Exception {
        // Get the systemAnnouncement
        restSystemAnnouncementMockMvc.perform(get("/api/messages/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSystemAnnouncement() throws Exception {
        // Initialize the database
        repository.saveAndFlush(systemAnnouncement);
		
		int databaseSizeBeforeUpdate = repository.findAll().size();

        // Update the systemAnnouncement
        systemAnnouncement.setAssignmentType(UPDATED_ASSIGNMENT_TYPE);
        systemAnnouncement.setSubject(UPDATED_SUBJECT);
        systemAnnouncement.setMessage(UPDATED_MESSAGE);
        restSystemAnnouncementMockMvc.perform(put("/api/messages")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(systemAnnouncement)))
                .andExpect(status().isOk());

        // Validate the SystemAnnouncement in the database
        List<SystemAnnouncement> systemAnnouncements = repository.findAll();
        assertThat(systemAnnouncements).hasSize(databaseSizeBeforeUpdate);
        SystemAnnouncement testSystemAnnouncement = systemAnnouncements.get(systemAnnouncements.size() - 1);
        assertThat(testSystemAnnouncement.getAssignmentType()).isEqualTo(UPDATED_ASSIGNMENT_TYPE);
        assertThat(testSystemAnnouncement.getSubject()).isEqualTo(UPDATED_SUBJECT);
        assertThat(testSystemAnnouncement.getMessage()).isEqualTo(UPDATED_MESSAGE);
    }

    @Test
    @Transactional
    public void deleteSystemAnnouncement() throws Exception {
        // Initialize the database
        repository.saveAndFlush(systemAnnouncement);
		
		int databaseSizeBeforeDelete = repository.findAll().size();

        // Get the systemAnnouncement
        restSystemAnnouncementMockMvc.perform(delete("/api/messages/{id}", systemAnnouncement.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<SystemAnnouncement> systemAnnouncements = repository.findAll();
        assertThat(systemAnnouncements).hasSize(databaseSizeBeforeDelete - 1);
    }
}
