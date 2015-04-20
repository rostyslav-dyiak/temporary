package com.kb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kb.domain.Eatery_member;
import com.kb.repository.Eatery_memberRepository;
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
 * REST controller for managing Eatery_member.
 */
@RestController
@RequestMapping("/api")
public class Eatery_memberResource {

    private final Logger log = LoggerFactory.getLogger(Eatery_memberResource.class);

    @Inject
    private Eatery_memberRepository eatery_memberRepository;

    /**
     * POST  /eatery_members -> Create a new eatery_member.
     */
    @RequestMapping(value = "/eatery_members",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody Eatery_member eatery_member) throws URISyntaxException {
        log.debug("REST request to save Eatery_member : {}", eatery_member);
        if (eatery_member.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new eatery_member cannot already have an ID").build();
        }
        eatery_memberRepository.save(eatery_member);
        return ResponseEntity.created(new URI("/api/eatery_members/" + eatery_member.getId())).build();
    }

    /**
     * PUT  /eatery_members -> Updates an existing eatery_member.
     */
    @RequestMapping(value = "/eatery_members",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody Eatery_member eatery_member) throws URISyntaxException {
        log.debug("REST request to update Eatery_member : {}", eatery_member);
        if (eatery_member.getId() == null) {
            return create(eatery_member);
        }
        eatery_memberRepository.save(eatery_member);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /eatery_members -> get all the eatery_members.
     */
    @RequestMapping(value = "/eatery_members",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Eatery_member> getAll() {
        log.debug("REST request to get all Eatery_members");
        return eatery_memberRepository.findAll();
    }

    /**
     * GET  /eatery_members/:id -> get the "id" eatery_member.
     */
    @RequestMapping(value = "/eatery_members/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Eatery_member> get(@PathVariable Long id) {
        log.debug("REST request to get Eatery_member : {}", id);
        return Optional.ofNullable(eatery_memberRepository.findOne(id))
            .map(eatery_member -> new ResponseEntity<>(
                eatery_member,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /eatery_members/:id -> delete the "id" eatery_member.
     */
    @RequestMapping(value = "/eatery_members/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Eatery_member : {}", id);
        eatery_memberRepository.delete(id);
    }
}
