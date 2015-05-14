package com.kb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kb.domain.SystemAnnouncementUser;
import com.kb.repository.SystemAnnouncementUserRepository;
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

@RestController
@RequestMapping("/api")
public class SystemAnnouncementUserResource {

    private final Logger log = LoggerFactory.getLogger(SystemAnnouncementUserResource.class);

    @Inject
    private SystemAnnouncementUserRepository repository;

    @RequestMapping(value = "/messages/{systemAnnouncementId}/users",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody final SystemAnnouncementUser entity) throws URISyntaxException {
        log.debug("REST request to save System AnnouncementUser : {}", entity);
        if (entity.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new entity cannot already have an ID").build();
        }
        repository.save(entity);
        return ResponseEntity.created(new URI("/api/messages/{systemAnnouncementId}/users/" + entity.getId())).build();
    }

    @RequestMapping(value = "/messages/{systemAnnouncementId}/users",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody final SystemAnnouncementUser entity) throws URISyntaxException {
        log.debug("REST request to update SystemAnnouncementUser : {}", entity);
        if (entity.getId() == null) {
            return create(entity);
        }
        repository.save(entity);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/messages/users",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<SystemAnnouncementUser>> getAll(@RequestParam(value = "page" , required = false) final Integer offset,
                                  @RequestParam(value = "per_page", required = false) final Integer limit)
        throws URISyntaxException {
        Page<SystemAnnouncementUser> page = repository.findAll(PaginationUtil.generatePageRequest(offset, limit));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/messages/users", offset, limit);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/messages/{systemAnnouncementId}/users/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<SystemAnnouncementUser> get(@PathVariable final Long id) {
        log.debug("REST request to get SystemAnnouncementUser : {}", id);
        return Optional.ofNullable(repository.findOne(id))
            .map(entity -> new ResponseEntity<>(
                entity,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/messages/{systemAnnouncementId}/users/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable final Long id) {
        log.debug("REST request to delete SystemAnnouncementUser : {}", id);
        repository.delete(id);
    }
}
