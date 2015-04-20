package com.kb.web.rest;

import com.kb.Application;
import com.kb.domain.Picture;
import com.kb.repository.PictureRepository;

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
 * Test class for the PictureResource REST controller.
 *
 * @see PictureResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class PictureResourceTest {

    private static final String DEFAULT_URL = "SAMPLE_TEXT";
    private static final String UPDATED_URL = "UPDATED_TEXT";
    private static final String DEFAULT_DESCRIPTION = "SAMPLE_TEXT";
    private static final String UPDATED_DESCRIPTION = "UPDATED_TEXT";

    @Inject
    private PictureRepository pictureRepository;

    private MockMvc restPictureMockMvc;

    private Picture picture;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PictureResource pictureResource = new PictureResource();
        ReflectionTestUtils.setField(pictureResource, "pictureRepository", pictureRepository);
        this.restPictureMockMvc = MockMvcBuilders.standaloneSetup(pictureResource).build();
    }

    @Before
    public void initTest() {
        picture = new Picture();
        picture.setUrl(DEFAULT_URL);
        picture.setDescription(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createPicture() throws Exception {
        int databaseSizeBeforeCreate = pictureRepository.findAll().size();

        // Create the Picture
        restPictureMockMvc.perform(post("/api/pictures")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(picture)))
                .andExpect(status().isCreated());

        // Validate the Picture in the database
        List<Picture> pictures = pictureRepository.findAll();
        assertThat(pictures).hasSize(databaseSizeBeforeCreate + 1);
        Picture testPicture = pictures.get(pictures.size() - 1);
        assertThat(testPicture.getUrl()).isEqualTo(DEFAULT_URL);
        assertThat(testPicture.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllPictures() throws Exception {
        // Initialize the database
        pictureRepository.saveAndFlush(picture);

        // Get all the pictures
        restPictureMockMvc.perform(get("/api/pictures"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(picture.getId().intValue())))
                .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL.toString())))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    @Test
    @Transactional
    public void getPicture() throws Exception {
        // Initialize the database
        pictureRepository.saveAndFlush(picture);

        // Get the picture
        restPictureMockMvc.perform(get("/api/pictures/{id}", picture.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(picture.getId().intValue()))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPicture() throws Exception {
        // Get the picture
        restPictureMockMvc.perform(get("/api/pictures/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePicture() throws Exception {
        // Initialize the database
        pictureRepository.saveAndFlush(picture);
		
		int databaseSizeBeforeUpdate = pictureRepository.findAll().size();

        // Update the picture
        picture.setUrl(UPDATED_URL);
        picture.setDescription(UPDATED_DESCRIPTION);
        restPictureMockMvc.perform(put("/api/pictures")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(picture)))
                .andExpect(status().isOk());

        // Validate the Picture in the database
        List<Picture> pictures = pictureRepository.findAll();
        assertThat(pictures).hasSize(databaseSizeBeforeUpdate);
        Picture testPicture = pictures.get(pictures.size() - 1);
        assertThat(testPicture.getUrl()).isEqualTo(UPDATED_URL);
        assertThat(testPicture.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void deletePicture() throws Exception {
        // Initialize the database
        pictureRepository.saveAndFlush(picture);
		
		int databaseSizeBeforeDelete = pictureRepository.findAll().size();

        // Get the picture
        restPictureMockMvc.perform(delete("/api/pictures/{id}", picture.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Picture> pictures = pictureRepository.findAll();
        assertThat(pictures).hasSize(databaseSizeBeforeDelete - 1);
    }
}
