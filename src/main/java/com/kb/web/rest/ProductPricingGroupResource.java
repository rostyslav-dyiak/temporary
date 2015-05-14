package com.kb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kb.domain.ProductPricingGroup;
import com.kb.repository.ProductPricingGroupRepository;
import com.kb.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
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
 * REST controller for managing ProductPricingGroup.
 */
@RestController
@RequestMapping("/api")
public class ProductPricingGroupResource {

    private final Logger log = LoggerFactory.getLogger(ProductPricingGroupResource.class);

    @Inject
    private ProductPricingGroupRepository productPricingGroupRepository;

    /**
     * POST  /productPricingGroups -> Create a new productPricingGroup.
     */
    @RequestMapping(value = "/productPricingGroups",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody ProductPricingGroup productPricingGroup) throws URISyntaxException {
        log.debug("REST request to save ProductPricingGroup : {}", productPricingGroup);
        if (productPricingGroup.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new productPricingGroup cannot already have an ID").build();
        }
        productPricingGroupRepository.save(productPricingGroup);
        return ResponseEntity.created(new URI("/api/productPricingGroups/" + productPricingGroup.getId())).build();
    }

    /**
     * PUT  /productPricingGroups -> Updates an existing productPricingGroup.
     */
    @RequestMapping(value = "/productPricingGroups",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody ProductPricingGroup productPricingGroup) throws URISyntaxException {
        log.debug("REST request to update ProductPricingGroup : {}", productPricingGroup);
        if (productPricingGroup.getId() == null) {
            return create(productPricingGroup);
        }
        productPricingGroupRepository.save(productPricingGroup);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /productPricingGroups -> get all the productPricingGroups.
     */
    @RequestMapping(value = "/productPricingGroups",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<ProductPricingGroup>> getAll(@RequestParam(value = "page" , required = false) Integer offset,
                                  @RequestParam(value = "per_page", required = false) Integer limit)
        throws URISyntaxException {
        Page<ProductPricingGroup> page = productPricingGroupRepository.findAll(PaginationUtil.generatePageRequest(offset, limit));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/productPricingGroups", offset, limit);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /productPricingGroups/:id -> get the "id" productPricingGroup.
     */
    @RequestMapping(value = "/productPricingGroups/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ProductPricingGroup> get(@PathVariable Long id) {
        log.debug("REST request to get ProductPricingGroup : {}", id);
        return Optional.ofNullable(productPricingGroupRepository.findOne(id))
            .map(productPricingGroup -> new ResponseEntity<>(
                productPricingGroup,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /productPricingGroups/:id -> delete the "id" productPricingGroup.
     */
    @RequestMapping(value = "/productPricingGroups/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete ProductPricingGroup : {}", id);
        productPricingGroupRepository.delete(id);
    }
}
