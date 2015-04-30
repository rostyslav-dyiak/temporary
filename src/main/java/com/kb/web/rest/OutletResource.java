package com.kb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kb.domain.Outlet;
import com.kb.domain.User;
import com.kb.repository.OutletRepository;
import com.kb.repository.UserRepository;
import com.kb.security.SecurityUtils;
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
 * REST controller for managing Outlet.
 */
@RestController
@RequestMapping("/api")
public class OutletResource {

    private final Logger log = LoggerFactory.getLogger(OutletResource.class);

    @Inject
    private OutletRepository outletRepository;

    @Inject
    private UserRepository userRepository;

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
    public ResponseEntity<List<Outlet>> getAll(@RequestParam(value = "page", required = false) Integer offset,
                                               @RequestParam(value = "per_page", required = false) Integer limit)
        throws URISyntaxException {
        Page<Outlet> page = outletRepository.findAll(PaginationUtil.generatePageRequest(offset, limit));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/outlets", offset, limit);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /outlets -> get all the outlets.
     */
    @RequestMapping(value = "/outlets/company",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Outlet>> getAllByCompany(@RequestParam(value = "page", required = false) Integer offset,
                                                        @RequestParam(value = "per_page", required = false) Integer limit)
        throws URISyntaxException {
        Optional<User> optionUser = userRepository.findOneByEmail(SecurityUtils.getCurrentLogin());
        if (optionUser.isPresent()) {
            User user = optionUser.get();
            Page<Outlet> page = outletRepository.findByCompany(user.getCompany(), PaginationUtil.generatePageRequest(offset, limit));
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/outlets", offset, limit);
            return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
        }
        return null;
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
