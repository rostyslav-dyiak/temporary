package com.kb.web.rest;

import com.kb.Application;
import com.kb.domain.ProductPricingGroup;
import com.kb.repository.ProductPricingGroupRepository;
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

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ProductPricingGroupResource REST controller.
 *
 * @see ProductPricingGroupResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ProductPricingGroupResourceTest {


    private static final BigDecimal DEFAULT_PRICE = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_PRICE = BigDecimal.ONE;

    @Inject
    private ProductPricingGroupRepository productPricingGroupRepository;

    private MockMvc restProductPricingGroupMockMvc;

    private ProductPricingGroup productPricingGroup;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ProductPricingGroupResource productPricingGroupResource = new ProductPricingGroupResource();
        ReflectionTestUtils.setField(productPricingGroupResource, "productPricingGroupRepository", productPricingGroupRepository);
        this.restProductPricingGroupMockMvc = MockMvcBuilders.standaloneSetup(productPricingGroupResource).build();
    }

    @Before
    public void initTest() {
        productPricingGroup = new ProductPricingGroup();
        productPricingGroup.setPrice(DEFAULT_PRICE);
    }

    @Test
    @Transactional
    public void createProductPricingGroup() throws Exception {
        int databaseSizeBeforeCreate = productPricingGroupRepository.findAll().size();

        // Create the ProductPricingGroup
        restProductPricingGroupMockMvc.perform(post("/api/productPricingGroups")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(productPricingGroup)))
                .andExpect(status().isCreated());

        // Validate the ProductPricingGroup in the database
        List<ProductPricingGroup> productPricingGroups = productPricingGroupRepository.findAll();
        assertThat(productPricingGroups).hasSize(databaseSizeBeforeCreate + 1);
        ProductPricingGroup testProductPricingGroup = productPricingGroups.get(productPricingGroups.size() - 1);
        assertThat(testProductPricingGroup.getPrice()).isEqualTo(DEFAULT_PRICE);
    }

    @Test
    @Transactional
    public void getAllProductPricingGroups() throws Exception {
        // Initialize the database
        productPricingGroupRepository.saveAndFlush(productPricingGroup);

        // Get all the productPricingGroups
        restProductPricingGroupMockMvc.perform(get("/api/productPricingGroups"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(productPricingGroup.getId().intValue())))
                .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.intValue())));
    }

    @Test
    @Transactional
    public void getProductPricingGroup() throws Exception {
        // Initialize the database
        productPricingGroupRepository.saveAndFlush(productPricingGroup);

        // Get the productPricingGroup
        restProductPricingGroupMockMvc.perform(get("/api/productPricingGroups/{id}", productPricingGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(productPricingGroup.getId().intValue()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingProductPricingGroup() throws Exception {
        // Get the productPricingGroup
        restProductPricingGroupMockMvc.perform(get("/api/productPricingGroups/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProductPricingGroup() throws Exception {
        // Initialize the database
        productPricingGroupRepository.saveAndFlush(productPricingGroup);
		
		int databaseSizeBeforeUpdate = productPricingGroupRepository.findAll().size();

        // Update the productPricingGroup
        productPricingGroup.setPrice(UPDATED_PRICE);
        restProductPricingGroupMockMvc.perform(put("/api/productPricingGroups")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(productPricingGroup)))
                .andExpect(status().isOk());

        // Validate the ProductPricingGroup in the database
        List<ProductPricingGroup> productPricingGroups = productPricingGroupRepository.findAll();
        assertThat(productPricingGroups).hasSize(databaseSizeBeforeUpdate);
        ProductPricingGroup testProductPricingGroup = productPricingGroups.get(productPricingGroups.size() - 1);
        assertThat(testProductPricingGroup.getPrice()).isEqualTo(UPDATED_PRICE);
    }

    @Test
    @Transactional
    public void deleteProductPricingGroup() throws Exception {
        // Initialize the database
        productPricingGroupRepository.saveAndFlush(productPricingGroup);
		
		int databaseSizeBeforeDelete = productPricingGroupRepository.findAll().size();

        // Get the productPricingGroup
        restProductPricingGroupMockMvc.perform(delete("/api/productPricingGroups/{id}", productPricingGroup.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<ProductPricingGroup> productPricingGroups = productPricingGroupRepository.findAll();
        assertThat(productPricingGroups).hasSize(databaseSizeBeforeDelete - 1);
    }
}
