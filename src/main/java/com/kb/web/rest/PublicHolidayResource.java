package com.kb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kb.domain.PublicHoliday;
import com.kb.repository.PublicHolidayRepository;
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
 * REST controller for managing PublicHoliday.
 */
@RestController
@RequestMapping("/api")
public class PublicHolidayResource {

    private final Logger log = LoggerFactory.getLogger(PublicHolidayResource.class);

    @Inject
    private PublicHolidayRepository publicHolidayRepository;

    @RequestMapping(value = "/holidays",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody final PublicHoliday publicHoliday) throws URISyntaxException {
        log.debug("REST request to save PublicHoliday : {}", publicHoliday);
        if (publicHoliday.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new publicHoliday cannot already have an ID").build();
        }
        publicHolidayRepository.save(publicHoliday);
        return ResponseEntity.created(new URI("/api/holidays/" + publicHoliday.getId())).build();
    }

    @RequestMapping(value = "/holidays",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody final PublicHoliday publicHoliday) throws URISyntaxException {
        log.debug("REST request to update PublicHoliday : {}", publicHoliday);
        if (publicHoliday.getId() == null) {
            return create(publicHoliday);
        }
        publicHolidayRepository.save(publicHoliday);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/holidays",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<PublicHoliday>> getAll(@RequestParam(value = "page" , required = false) final Integer offset,
                                  @RequestParam(value = "per_page", required = false) final Integer limit)
        throws URISyntaxException {
        Page<PublicHoliday> page = publicHolidayRepository.findAll(PaginationUtil.generatePageRequest(offset, limit));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/holidays", offset, limit);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/holidays/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PublicHoliday> get(@PathVariable final Long id) {
        log.debug("REST request to get PublicHoliday : {}", id);
        return Optional.ofNullable(publicHolidayRepository.findOne(id))
            .map(publicHoliday -> new ResponseEntity<>(
                publicHoliday,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/holidays/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable final Long id) {
        log.debug("REST request to delete PublicHoliday : {}", id);
        publicHolidayRepository.delete(id);
    }
}
