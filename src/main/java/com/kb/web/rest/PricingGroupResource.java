package com.kb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kb.domain.PricingGroup;
import com.kb.repository.PricingGroupRepository;
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
 * REST controller for managing PricingGroup.
 */
@RestController
@RequestMapping("/api")
public class PricingGroupResource {

    private final Logger log = LoggerFactory.getLogger(PricingGroupResource.class);

    @Inject
    private PricingGroupRepository pricingGroupRepository;

    /**
     * POST  /pricingGroups -> Create a new pricingGroup.
     */
    @RequestMapping(value = "/pricingGroups",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody PricingGroup pricingGroup) throws URISyntaxException {
        log.debug("REST request to save PricingGroup : {}", pricingGroup);
        if (pricingGroup.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new pricingGroup cannot already have an ID").build();
        }
        pricingGroupRepository.save(pricingGroup);
        return ResponseEntity.created(new URI("/api/pricingGroups/" + pricingGroup.getId())).build();
    }

    /**
     * PUT  /pricingGroups -> Updates an existing pricingGroup.
     */
    @RequestMapping(value = "/pricingGroups",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody PricingGroup pricingGroup) throws URISyntaxException {
        log.debug("REST request to update PricingGroup : {}", pricingGroup);
        if (pricingGroup.getId() == null) {
            return create(pricingGroup);
        }
        pricingGroupRepository.save(pricingGroup);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /pricingGroups -> get all the pricingGroups.
     */
    @RequestMapping(value = "/pricingGroups",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<PricingGroup>> getAll(@RequestParam(value = "page" , required = false) Integer offset,
                                  @RequestParam(value = "per_page", required = false) Integer limit)
        throws URISyntaxException {
        Page<PricingGroup> page = pricingGroupRepository.findAll(PaginationUtil.generatePageRequest(offset, limit));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pricingGroups", offset, limit);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /pricingGroups/:id -> get the "id" pricingGroup.
     */
    @RequestMapping(value = "/pricingGroups/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PricingGroup> get(@PathVariable Long id) {
        log.debug("REST request to get PricingGroup : {}", id);
        return Optional.ofNullable(pricingGroupRepository.findOne(id))
            .map(pricingGroup -> new ResponseEntity<>(
                pricingGroup,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /pricingGroups/:id -> delete the "id" pricingGroup.
     */
    @RequestMapping(value = "/pricingGroups/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete PricingGroup : {}", id);
        pricingGroupRepository.delete(id);
    }
}
