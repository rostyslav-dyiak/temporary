package com.kb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kb.domain.Eatery;
import com.kb.repository.EateryRepository;
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
 * REST controller for managing Eatery.
 */
@RestController
@RequestMapping("/api")
public class EateryResource {

    private final Logger log = LoggerFactory.getLogger(EateryResource.class);

    @Inject
    private EateryRepository eateryRepository;

    /**
     * POST  /eaterys -> Create a new eatery.
     */
    @RequestMapping(value = "/eaterys",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody Eatery eatery) throws URISyntaxException {
        log.debug("REST request to save Eatery : {}", eatery);
        if (eatery.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new eatery cannot already have an ID").build();
        }
        eateryRepository.save(eatery);
        return ResponseEntity.created(new URI("/api/eaterys/" + eatery.getId())).build();
    }

    /**
     * PUT  /eaterys -> Updates an existing eatery.
     */
    @RequestMapping(value = "/eaterys",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody Eatery eatery) throws URISyntaxException {
        log.debug("REST request to update Eatery : {}", eatery);
        if (eatery.getId() == null) {
            return create(eatery);
        }
        eateryRepository.save(eatery);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /eaterys -> get all the eaterys.
     */
    @RequestMapping(value = "/eaterys",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Eatery> getAll() {
        log.debug("REST request to get all Eaterys");
        return eateryRepository.findAll();
    }

    /**
     * GET  /eaterys/:id -> get the "id" eatery.
     */
    @RequestMapping(value = "/eaterys/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Eatery> get(@PathVariable Long id) {
        log.debug("REST request to get Eatery : {}", id);
        return Optional.ofNullable(eateryRepository.findOne(id))
            .map(eatery -> new ResponseEntity<>(
                eatery,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /eaterys/:id -> delete the "id" eatery.
     */
    @RequestMapping(value = "/eaterys/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Eatery : {}", id);
        eateryRepository.delete(id);
    }
}
