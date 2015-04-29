package com.kb.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.kb.service.dayoff.DayOffService;
import com.kb.web.rest.dto.dayoff.AggregatedDayOffDto;
import com.kb.web.rest.dto.dayoff.DayOffDto;
import com.kb.web.rest.util.PaginationUtil;

/**
 * REST controller for managing SupplierDetailsPublicHolidays.
 */
@RestController
@RequestMapping("/api")
public class DayOffResource {
    private final Logger log = LoggerFactory.getLogger(DayOffResource.class);

    @Inject
    private DayOffService service;
    
    @RequestMapping(value = "/dayoffs",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@Valid @RequestBody final DayOffDto dto) throws URISyntaxException {
        log.debug("REST request to save SupplierDetailsPublicHolidays : {}", dto);
        if (dto.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new Day Off cannot already have an ID").build();
        }
        service.save(dto);
        return ResponseEntity.created(new URI("/api/dayoffs/" + dto.getId())).build();
    }

    @RequestMapping(value = "/dayoffs",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@Valid @RequestBody final DayOffDto dto) throws URISyntaxException {
        log.debug("REST request to update Day Offs : {}", dto);
        if (dto.getId() == null) {
            return create(dto);
        }
        service.save(dto);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/dayoffs",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<AggregatedDayOffDto> getAll(@RequestParam(value = "page" , required = false) final Integer offset,
                                  @RequestParam(value = "per_page", required = false) final Integer limit)
        throws URISyntaxException {
    	
    	AggregatedDayOffDto page = service.findAll(PaginationUtil.generatePageRequest(offset, limit));
    	
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @RequestMapping(value = "/dayoffs/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DayOffDto> get(@PathVariable final Long id) {
        log.debug("REST request to get Day Offs : {}", id);
        return Optional.ofNullable(service.findOne(id))
            .map(dto -> new ResponseEntity<>(
                dto,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/dayoffs/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable final Long id) {
        log.debug("REST request to delete SupplierDetailsPublicHolidays : {}", id);
        service.delete(id);
    }
}
