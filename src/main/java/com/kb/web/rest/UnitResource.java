package com.kb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kb.domain.Unit;
import com.kb.repository.UnitRepository;
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
 * REST controller for managing Unit.
 */
@RestController
@RequestMapping("/api")
public class UnitResource {

    private final Logger log = LoggerFactory.getLogger(UnitResource.class);

    @Inject
    private UnitRepository unitRepository;

    /**
     * POST  /units -> Create a new unit.
     */
    @RequestMapping(value = "/units",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody Unit unit) throws URISyntaxException {
        log.debug("REST request to save Unit : {}", unit);
        if (unit.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new unit cannot already have an ID").build();
        }
        unitRepository.save(unit);
        return ResponseEntity.created(new URI("/api/units/" + unit.getId())).build();
    }

    /**
     * PUT  /units -> Updates an existing unit.
     */
    @RequestMapping(value = "/units",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody Unit unit) throws URISyntaxException {
        log.debug("REST request to update Unit : {}", unit);
        if (unit.getId() == null) {
            return create(unit);
        }
        unitRepository.save(unit);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /units -> get all the units.
     */
    @RequestMapping(value = "/units",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Unit> getAll() {
        log.debug("REST request to get all Units");
        return unitRepository.findAll();
    }

    /**
     * GET  /units/:id -> get the "id" unit.
     */
    @RequestMapping(value = "/units/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Unit> get(@PathVariable Long id) {
        log.debug("REST request to get Unit : {}", id);
        return Optional.ofNullable(unitRepository.findOne(id))
            .map(unit -> new ResponseEntity<>(
                unit,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /units/:id -> delete the "id" unit.
     */
    @RequestMapping(value = "/units/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Unit : {}", id);
        unitRepository.delete(id);
    }
}
