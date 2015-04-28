package com.kb.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
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
import com.kb.domain.SupplierDetailsPublicHolidays;
import com.kb.repository.SupplierDetailsPublicHolidaysRepository;
import com.kb.web.rest.util.PaginationUtil;

/**
 * REST controller for managing SupplierDetailsPublicHolidays.
 */
@RestController
@RequestMapping("/api")
public class SupplierDetailsPublicHolidaysResource {

    private final Logger log = LoggerFactory.getLogger(SupplierDetailsPublicHolidaysResource.class);

    @Inject
    private SupplierDetailsPublicHolidaysRepository supplierDetailsPublicHolidaysRepository;

    @RequestMapping(value = "/supplierdetails/{supplier_details_id}/holidays/{public_holiday_id}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody final SupplierDetailsPublicHolidays supplierDetailsPublicHolidays) throws URISyntaxException {
        log.debug("REST request to save SupplierDetailsPublicHolidays : {}", supplierDetailsPublicHolidays);
        if (supplierDetailsPublicHolidays.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new supplierDetailsPublicHolidays cannot already have an ID").build();
        }
        supplierDetailsPublicHolidaysRepository.save(supplierDetailsPublicHolidays);
        return ResponseEntity.created(new URI("/api/supplierDetailsPublicHolidayss/" + supplierDetailsPublicHolidays.getId())).build();
    }

    @RequestMapping(value = "/supplierdetails/{supplier_details_id}/holidays/{public_holiday_id}",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody final SupplierDetailsPublicHolidays supplierDetailsPublicHolidays) throws URISyntaxException {
        log.debug("REST request to update SupplierDetailsPublicHolidays : {}", supplierDetailsPublicHolidays);
        if (supplierDetailsPublicHolidays.getId() == null) {
            return create(supplierDetailsPublicHolidays);
        }
        supplierDetailsPublicHolidaysRepository.save(supplierDetailsPublicHolidays);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/supplierdetails/{supplier_details_id}/holidays/{public_holiday_id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<SupplierDetailsPublicHolidays>> getAll(@RequestParam(value = "page" , required = false) final Integer offset,
                                  @RequestParam(value = "per_page", required = false) final Integer limit)
        throws URISyntaxException {
        Page<SupplierDetailsPublicHolidays> page = supplierDetailsPublicHolidaysRepository.findAll(PaginationUtil.generatePageRequest(offset, limit));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/supplierDetailsPublicHolidayss", offset, limit);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/supplierdetails/{supplier_details_id}/holidays/{public_holiday_id}/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<SupplierDetailsPublicHolidays> get(@PathVariable final Long id) {
        log.debug("REST request to get SupplierDetailsPublicHolidays : {}", id);
        return Optional.ofNullable(supplierDetailsPublicHolidaysRepository.findOne(id))
            .map(supplierDetailsPublicHolidays -> new ResponseEntity<>(
                supplierDetailsPublicHolidays,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/supplierdetails/{supplier_details_id}/holidays/{public_holiday_id}/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable final Long id) {
        log.debug("REST request to delete SupplierDetailsPublicHolidays : {}", id);
        supplierDetailsPublicHolidaysRepository.delete(id);
    }
}
