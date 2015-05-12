package com.kb.web.rest;

import com.kb.Application;
import com.kb.domain.Product;
import com.kb.repository.ProductRepository;

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
 * Test class for the ProductResource REST controller.
 *
 * @see ProductResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ProductResourceTest {

    private static final String DEFAULT_TITLE = "SAMPLE_TEXT";
    private static final String UPDATED_TITLE = "UPDATED_TEXT";
    private static final String DEFAULT_DESCRIPTION = "SAMPLE_TEXT";
    private static final String UPDATED_DESCRIPTION = "UPDATED_TEXT";
    private static final String DEFAULT_BRAND = "SAMPLE_TEXT";
    private static final String UPDATED_BRAND = "UPDATED_TEXT";
    private static final String DEFAULT_ORIGIN = "SAMPLE_TEXT";
    private static final String UPDATED_ORIGIN = "UPDATED_TEXT";

    private static final Boolean DEFAULT_CERTIFIED_HALAL = false;
    private static final Boolean UPDATED_CERTIFIED_HALAL = true;
    private static final String DEFAULT_UNIT_DESCRIPTION = "SAMPLE_TEXT";
    private static final String UPDATED_UNIT_DESCRIPTION = "UPDATED_TEXT";

    private static final Boolean DEFAULT_UNIT_HIDE = false;
    private static final Boolean UPDATED_UNIT_HIDE = true;

    private static final Boolean DEFAULT_AVAILABLE = false;
    private static final Boolean UPDATED_AVAILABLE = true;
    private static final String DEFAULT_CODE = "SAMPLE_TEXT";
    private static final String UPDATED_CODE = "UPDATED_TEXT";

    private static final Boolean DEFAULT_CODE_GENERATE = false;
    private static final Boolean UPDATED_CODE_GENERATE = true;

    @Inject
    private ProductRepository productRepository;

    private MockMvc restProductMockMvc;

    private Product product;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ProductResource productResource = new ProductResource();
        ReflectionTestUtils.setField(productResource, "productRepository", productRepository);
        this.restProductMockMvc = MockMvcBuilders.standaloneSetup(productResource).build();
    }

    @Before
    public void initTest() {
        product = new Product();
        product.setTitle(DEFAULT_TITLE);
        product.setDescription(DEFAULT_DESCRIPTION);
        product.setBrand(DEFAULT_BRAND);
        product.setOrigin(DEFAULT_ORIGIN);
        product.setCertifiedHalal(DEFAULT_CERTIFIED_HALAL);
        product.setUnitDescription(DEFAULT_UNIT_DESCRIPTION);
        product.setUnitHide(DEFAULT_UNIT_HIDE);
        product.setAvailable(DEFAULT_AVAILABLE);
        product.setCode(DEFAULT_CODE);
        product.setCodeGenerate(DEFAULT_CODE_GENERATE);
    }

    @Test
    @Transactional
    public void createProduct() throws Exception {
        int databaseSizeBeforeCreate = productRepository.findAll().size();

        // Create the Product
        restProductMockMvc.perform(post("/api/products")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(product)))
                .andExpect(status().isCreated());

        // Validate the Product in the database
        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(databaseSizeBeforeCreate + 1);
        Product testProduct = products.get(products.size() - 1);
        assertThat(testProduct.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testProduct.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testProduct.getBrand()).isEqualTo(DEFAULT_BRAND);
        assertThat(testProduct.getOrigin()).isEqualTo(DEFAULT_ORIGIN);
        assertThat(testProduct.getCertifiedHalal()).isEqualTo(DEFAULT_CERTIFIED_HALAL);
        assertThat(testProduct.getUnitDescription()).isEqualTo(DEFAULT_UNIT_DESCRIPTION);
        assertThat(testProduct.getUnitHide()).isEqualTo(DEFAULT_UNIT_HIDE);
        assertThat(testProduct.getAvailable()).isEqualTo(DEFAULT_AVAILABLE);
        assertThat(testProduct.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testProduct.getCodeGenerate()).isEqualTo(DEFAULT_CODE_GENERATE);
    }

    @Test
    @Transactional
    public void getAllProducts() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the products
        restProductMockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(product.getId().intValue())))
                .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
                .andExpect(jsonPath("$.[*].brand").value(hasItem(DEFAULT_BRAND.toString())))
                .andExpect(jsonPath("$.[*].origin").value(hasItem(DEFAULT_ORIGIN.toString())))
                .andExpect(jsonPath("$.[*].certifiedHalal").value(hasItem(DEFAULT_CERTIFIED_HALAL.booleanValue())))
                .andExpect(jsonPath("$.[*].unitDescription").value(hasItem(DEFAULT_UNIT_DESCRIPTION.toString())))
                .andExpect(jsonPath("$.[*].unitHide").value(hasItem(DEFAULT_UNIT_HIDE.booleanValue())))
                .andExpect(jsonPath("$.[*].available").value(hasItem(DEFAULT_AVAILABLE.booleanValue())))
                .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
                .andExpect(jsonPath("$.[*].codeGenerate").value(hasItem(DEFAULT_CODE_GENERATE.booleanValue())));
    }

    @Test
    @Transactional
    public void getProduct() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get the product
        restProductMockMvc.perform(get("/api/products/{id}", product.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(product.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.brand").value(DEFAULT_BRAND.toString()))
            .andExpect(jsonPath("$.origin").value(DEFAULT_ORIGIN.toString()))
            .andExpect(jsonPath("$.certifiedHalal").value(DEFAULT_CERTIFIED_HALAL.booleanValue()))
            .andExpect(jsonPath("$.unitDescription").value(DEFAULT_UNIT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.unitHide").value(DEFAULT_UNIT_HIDE.booleanValue()))
            .andExpect(jsonPath("$.available").value(DEFAULT_AVAILABLE.booleanValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.codeGenerate").value(DEFAULT_CODE_GENERATE.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingProduct() throws Exception {
        // Get the product
        restProductMockMvc.perform(get("/api/products/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProduct() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

		int databaseSizeBeforeUpdate = productRepository.findAll().size();

        // Update the product
        product.setTitle(UPDATED_TITLE);
        product.setDescription(UPDATED_DESCRIPTION);
        product.setBrand(UPDATED_BRAND);
        product.setOrigin(UPDATED_ORIGIN);
        product.setCertifiedHalal(UPDATED_CERTIFIED_HALAL);
        product.setUnitDescription(UPDATED_UNIT_DESCRIPTION);
        product.setUnitHide(UPDATED_UNIT_HIDE);
        product.setAvailable(UPDATED_AVAILABLE);
        product.setCode(UPDATED_CODE);
        product.setCodeGenerate(UPDATED_CODE_GENERATE);
        restProductMockMvc.perform(put("/api/products")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(product)))
                .andExpect(status().isOk());

        // Validate the Product in the database
        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(databaseSizeBeforeUpdate);
        Product testProduct = products.get(products.size() - 1);
        assertThat(testProduct.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testProduct.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testProduct.getBrand()).isEqualTo(UPDATED_BRAND);
        assertThat(testProduct.getOrigin()).isEqualTo(UPDATED_ORIGIN);
        assertThat(testProduct.getCertifiedHalal()).isEqualTo(UPDATED_CERTIFIED_HALAL);
        assertThat(testProduct.getUnitDescription()).isEqualTo(UPDATED_UNIT_DESCRIPTION);
        assertThat(testProduct.getUnitHide()).isEqualTo(UPDATED_UNIT_HIDE);
        assertThat(testProduct.getAvailable()).isEqualTo(UPDATED_AVAILABLE);
        assertThat(testProduct.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testProduct.getCodeGenerate()).isEqualTo(UPDATED_CODE_GENERATE);
    }

    @Test
    @Transactional
    public void deleteProduct() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

		int databaseSizeBeforeDelete = productRepository.findAll().size();

        // Get the product
        restProductMockMvc.perform(delete("/api/products/{id}", product.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(databaseSizeBeforeDelete - 1);
    }
}
