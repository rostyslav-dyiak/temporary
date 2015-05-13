package com.kb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kb.domain.ProductAlias;
import com.kb.repository.ProductAliasRepository;
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
 * REST controller for managing ProductAlias.
 */
@RestController
@RequestMapping("/api")
public class ProductAliasResource {

    private final Logger log = LoggerFactory.getLogger(ProductAliasResource.class);

    @Inject
    private ProductAliasRepository productAliasRepository;

    /**
     * POST  /productAliass -> Create a new productAlias.
     */
    @RequestMapping(value = "/productAliass",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody ProductAlias productAlias) throws URISyntaxException {
        log.debug("REST request to save ProductAlias : {}", productAlias);
        if (productAlias.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new productAlias cannot already have an ID").build();
        }
        productAliasRepository.save(productAlias);
        return ResponseEntity.created(new URI("/api/productAliass/" + productAlias.getId())).build();
    }

    /**
     * PUT  /productAliass -> Updates an existing productAlias.
     */
    @RequestMapping(value = "/productAliass",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody ProductAlias productAlias) throws URISyntaxException {
        log.debug("REST request to update ProductAlias : {}", productAlias);
        if (productAlias.getId() == null) {
            return create(productAlias);
        }
        productAliasRepository.save(productAlias);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /productAliass -> get all the productAliass.
     */
    @RequestMapping(value = "/productAliass",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<ProductAlias> getAll() {
        log.debug("REST request to get all ProductAliass");
        return productAliasRepository.findAll();
    }

    /**
     * GET  /productAliass/:id -> get the "id" productAlias.
     */
    @RequestMapping(value = "/productAliass/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ProductAlias> get(@PathVariable Long id) {
        log.debug("REST request to get ProductAlias : {}", id);
        return Optional.ofNullable(productAliasRepository.findOne(id))
            .map(productAlias -> new ResponseEntity<>(
                productAlias,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /productAliass/:id -> delete the "id" productAlias.
     */
    @RequestMapping(value = "/productAliass/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete ProductAlias : {}", id);
        productAliasRepository.delete(id);
    }
}
