package com.kb.web.rest;

import com.kb.Application;
import com.kb.domain.Eatery_member;
import com.kb.repository.Eatery_memberRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
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

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the Eatery_memberResource REST controller.
 *
 * @see Eatery_memberResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class Eatery_memberResourceTest {

    private static final String DEFAULT_TITLE = "SAMPLE_TEXT";
    private static final String UPDATED_TITLE = "UPDATED_TEXT";
    private static final String DEFAULT_SALUTATION = "SAMPLE_TEXT";
    private static final String UPDATED_SALUTATION = "UPDATED_TEXT";
    private static final String DEFAULT_CONTACT_NUMBER = "SAMPLE_TEXT";
    private static final String UPDATED_CONTACT_NUMBER = "UPDATED_TEXT";
    private static final String DEFAULT_EMAIL = "SAMPLE_TEXT";
    private static final String UPDATED_EMAIL = "UPDATED_TEXT";

    private static final Boolean DEFAULT_STATUS = false;
    private static final Boolean UPDATED_STATUS = true;

    @Inject
    private Eatery_memberRepository eatery_memberRepository;

    private MockMvc restEatery_memberMockMvc;

    private Eatery_member eatery_member;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Eatery_memberResource eatery_memberResource = new Eatery_memberResource();
        ReflectionTestUtils.setField(eatery_memberResource, "eatery_memberRepository", eatery_memberRepository);
        this.restEatery_memberMockMvc = MockMvcBuilders.standaloneSetup(eatery_memberResource).build();
    }

    @Before
    public void initTest() {
        eatery_member = new Eatery_member();
        eatery_member.setTitle(DEFAULT_TITLE);
        eatery_member.setSalutation(DEFAULT_SALUTATION);
        eatery_member.setContact_number(DEFAULT_CONTACT_NUMBER);
        eatery_member.setEmail(DEFAULT_EMAIL);
        eatery_member.setStatus(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createEatery_member() throws Exception {
        int databaseSizeBeforeCreate = eatery_memberRepository.findAll().size();

        // Create the Eatery_member
        restEatery_memberMockMvc.perform(post("/api/eatery_members")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(eatery_member)))
                .andExpect(status().isCreated());

        // Validate the Eatery_member in the database
        List<Eatery_member> eatery_members = eatery_memberRepository.findAll();
        assertThat(eatery_members).hasSize(databaseSizeBeforeCreate + 1);
        Eatery_member testEatery_member = eatery_members.get(eatery_members.size() - 1);
        assertThat(testEatery_member.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testEatery_member.getSalutation()).isEqualTo(DEFAULT_SALUTATION);
        assertThat(testEatery_member.getContact_number()).isEqualTo(DEFAULT_CONTACT_NUMBER);
        assertThat(testEatery_member.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testEatery_member.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void getAllEatery_members() throws Exception {
        // Initialize the database
        eatery_memberRepository.saveAndFlush(eatery_member);

        // Get all the eatery_members
        restEatery_memberMockMvc.perform(get("/api/eatery_members"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(eatery_member.getId().intValue())))
                .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
                .andExpect(jsonPath("$.[*].salutation").value(hasItem(DEFAULT_SALUTATION.toString())))
                .andExpect(jsonPath("$.[*].contact_number").value(hasItem(DEFAULT_CONTACT_NUMBER.toString())))
                .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
                .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.booleanValue())));
    }

    @Test
    @Transactional
    public void getEatery_member() throws Exception {
        // Initialize the database
        eatery_memberRepository.saveAndFlush(eatery_member);

        // Get the eatery_member
        restEatery_memberMockMvc.perform(get("/api/eatery_members/{id}", eatery_member.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(eatery_member.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.salutation").value(DEFAULT_SALUTATION.toString()))
            .andExpect(jsonPath("$.contact_number").value(DEFAULT_CONTACT_NUMBER.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingEatery_member() throws Exception {
        // Get the eatery_member
        restEatery_memberMockMvc.perform(get("/api/eatery_members/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEatery_member() throws Exception {
        // Initialize the database
        eatery_memberRepository.saveAndFlush(eatery_member);
		
		int databaseSizeBeforeUpdate = eatery_memberRepository.findAll().size();

        // Update the eatery_member
        eatery_member.setTitle(UPDATED_TITLE);
        eatery_member.setSalutation(UPDATED_SALUTATION);
        eatery_member.setContact_number(UPDATED_CONTACT_NUMBER);
        eatery_member.setEmail(UPDATED_EMAIL);
        eatery_member.setStatus(UPDATED_STATUS);
        restEatery_memberMockMvc.perform(put("/api/eatery_members")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(eatery_member)))
                .andExpect(status().isOk());

        // Validate the Eatery_member in the database
        List<Eatery_member> eatery_members = eatery_memberRepository.findAll();
        assertThat(eatery_members).hasSize(databaseSizeBeforeUpdate);
        Eatery_member testEatery_member = eatery_members.get(eatery_members.size() - 1);
        assertThat(testEatery_member.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testEatery_member.getSalutation()).isEqualTo(UPDATED_SALUTATION);
        assertThat(testEatery_member.getContact_number()).isEqualTo(UPDATED_CONTACT_NUMBER);
        assertThat(testEatery_member.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testEatery_member.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void deleteEatery_member() throws Exception {
        // Initialize the database
        eatery_memberRepository.saveAndFlush(eatery_member);
		
		int databaseSizeBeforeDelete = eatery_memberRepository.findAll().size();

        // Get the eatery_member
        restEatery_memberMockMvc.perform(delete("/api/eatery_members/{id}", eatery_member.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Eatery_member> eatery_members = eatery_memberRepository.findAll();
        assertThat(eatery_members).hasSize(databaseSizeBeforeDelete - 1);
    }
}
