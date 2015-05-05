package com.kb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kb.domain.Currency;
import com.kb.repository.CurrencyRepository;
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
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Currency.
 */
@RestController
@RequestMapping("/api")
public class CurrencyResource {

    private final Logger log = LoggerFactory.getLogger(CurrencyResource.class);

    @Inject
    private CurrencyRepository currencyRepository;

    /**
     * POST  /currencys -> Create a new currency.
     */
    @RequestMapping(value = "/currencies",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody Currency currency) throws URISyntaxException {
        log.debug("REST request to save Currency : {}", currency);
        if (currency.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new currency cannot already have an ID").build();
        }
        currencyRepository.save(currency);
        return ResponseEntity.created(new URI("/api/currencies/" + currency.getId())).build();
    }

    /**
     * PUT  /currencys -> Updates an existing currency.
     */
    @RequestMapping(value = "/currencies",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody Currency currency) throws URISyntaxException {
        log.debug("REST request to update Currency : {}", currency);
        if (currency.getId() == null) {
            return create(currency);
        }
        currencyRepository.save(currency);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /currencys -> get all the currencys.
     */
    @RequestMapping(value = "/currencies",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Currency>> getAll(@RequestParam(value = "page" , required = false) Integer offset,
                                  @RequestParam(value = "per_page", required = false) Integer limit)
        throws URISyntaxException {
        Page<Currency> page = currencyRepository.findAll(PaginationUtil.generatePageRequest(offset, limit));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/currencies", offset, limit);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /currencys/:id -> get the "id" currency.
     */
    @RequestMapping(value = "/currencies/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Currency> get(@PathVariable Long id) {
        log.debug("REST request to get Currency : {}", id);
        return Optional.ofNullable(currencyRepository.findOne(id))
            .map(currency -> new ResponseEntity<>(
                currency,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /currencys/:id -> delete the "id" currency.
     */
    @RequestMapping(value = "/currencies/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Currency : {}", id);
        currencyRepository.delete(id);
    }
}
