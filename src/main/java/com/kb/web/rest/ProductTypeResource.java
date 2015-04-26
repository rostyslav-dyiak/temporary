package com.kb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kb.domain.ProductType;
import com.kb.repository.ProductTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ProductType.
 */
@RestController
@RequestMapping("/api")
public class ProductTypeResource {

    private final Logger log = LoggerFactory.getLogger(ProductTypeResource.class);

    @Inject
    private ProductTypeRepository productTypeRepository;

    /**
     * POST  /productTypes -> Create a new productType.
     */
    @RequestMapping(value = "/productTypes",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody ProductType productType) throws URISyntaxException {
        log.debug("REST request to save ProductType : {}", productType);
        if (productType.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new productType cannot already have an ID").build();
        }
        productTypeRepository.save(productType);
        return ResponseEntity.created(new URI("/api/productTypes/" + productType.getId())).build();
    }

    /**
     * PUT  /productTypes -> Updates an existing productType.
     */
    @RequestMapping(value = "/productTypes",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody ProductType productType) throws URISyntaxException {
        log.debug("REST request to update ProductType : {}", productType);
        if (productType.getId() == null) {
            return create(productType);
        }
        productTypeRepository.save(productType);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /productTypes -> get all the productTypes.
     */
    @RequestMapping(value = "/productTypes",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<ProductType> getAll() {
        log.debug("REST request to get all ProductTypes");
        return productTypeRepository.findAll();
    }

    /**
     * GET  /productTypes/:id -> get the "id" productType.
     */
    @RequestMapping(value = "/productTypes/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ProductType> get(@PathVariable Long id) {
        log.debug("REST request to get ProductType : {}", id);
        return Optional.ofNullable(productTypeRepository.findOne(id))
            .map(productType -> new ResponseEntity<>(
                productType,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /productTypes/:id -> delete the "id" productType.
     */
    @RequestMapping(value = "/productTypes/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete ProductType : {}", id);
        productTypeRepository.delete(id);
    }
}
