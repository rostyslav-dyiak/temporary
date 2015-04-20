package com.kb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kb.domain.Outlet;
import com.kb.repository.OutletRepository;
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
 * REST controller for managing Outlet.
 */
@RestController
@RequestMapping("/api")
public class OutletResource {

    private final Logger log = LoggerFactory.getLogger(OutletResource.class);

    @Inject
    private OutletRepository outletRepository;

    /**
     * POST  /outlets -> Create a new outlet.
     */
    @RequestMapping(value = "/outlets",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody Outlet outlet) throws URISyntaxException {
        log.debug("REST request to save Outlet : {}", outlet);
        if (outlet.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new outlet cannot already have an ID").build();
        }
        outletRepository.save(outlet);
        return ResponseEntity.created(new URI("/api/outlets/" + outlet.getId())).build();
    }

    /**
     * PUT  /outlets -> Updates an existing outlet.
     */
    @RequestMapping(value = "/outlets",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody Outlet outlet) throws URISyntaxException {
        log.debug("REST request to update Outlet : {}", outlet);
        if (outlet.getId() == null) {
            return create(outlet);
        }
        outletRepository.save(outlet);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /outlets -> get all the outlets.
     */
    @RequestMapping(value = "/outlets",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Outlet> getAll() {
        log.debug("REST request to get all Outlets");
        return outletRepository.findAll();
    }

    /**
     * GET  /outlets/:id -> get the "id" outlet.
     */
    @RequestMapping(value = "/outlets/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Outlet> get(@PathVariable Long id) {
        log.debug("REST request to get Outlet : {}", id);
        return Optional.ofNullable(outletRepository.findOne(id))
            .map(outlet -> new ResponseEntity<>(
                outlet,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /outlets/:id -> delete the "id" outlet.
     */
    @RequestMapping(value = "/outlets/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Outlet : {}", id);
        outletRepository.delete(id);
    }
}
