package com.kb.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.validation.Valid;

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
import com.kb.domain.SystemAnnouncement;
import com.kb.repository.SystemAnnouncementRepository;
import com.kb.web.rest.util.PaginationUtil;

/**
 * REST controller for managing SystemAnnouncement.
 */
@RestController
@RequestMapping("/api")
public class SystemAnnouncementResource {
	private final Logger log = LoggerFactory.getLogger(SystemAnnouncementResource.class);

	@Inject
	private SystemAnnouncementRepository repository;

	@RequestMapping(value = "/messages", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<Void> create(
			@Valid @RequestBody final SystemAnnouncement systemAnnouncement)
			throws URISyntaxException {
		
		log.debug("REST request to save message : {}", systemAnnouncement);
		if (systemAnnouncement.getId() != null) {
			return ResponseEntity
					.badRequest()
					.header("Failure", "A new message cannot already have an ID")
					.build();
		}
		
		repository.save(systemAnnouncement);
		return ResponseEntity.created(new URI("/api/messages/" + systemAnnouncement.getId())).build();
	}

	@RequestMapping(value = "/messages", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<Void> update(
			@Valid @RequestBody final SystemAnnouncement systemAnnouncement)
			throws URISyntaxException {
		log.debug("REST request to update message : {}",
				systemAnnouncement);
		if (systemAnnouncement.getId() == null) {
			return create(systemAnnouncement);
		}
		repository.save(systemAnnouncement);
		return ResponseEntity.ok().build();
	}

	@RequestMapping(value = "/messages", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<List<SystemAnnouncement>> getAll(
			@RequestParam(value = "page", required = false) final Integer offset,
			@RequestParam(value = "per_page", required = false) final Integer limit)
			throws URISyntaxException {
		Page<SystemAnnouncement> page = repository.findAll(PaginationUtil.generatePageRequest(offset, limit));
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/messages", offset, limit);
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/messages/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<SystemAnnouncement> get(@PathVariable final Long id) {
		log.debug("REST request to get message : {}", id);
		return Optional
				.ofNullable(repository.findOne(id))
				.map(systemAnnouncement -> new ResponseEntity<>(
						systemAnnouncement, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@RequestMapping(value = "/messages/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public void delete(@PathVariable final Long id) {
		log.debug("REST request to delete message : {}", id);
		repository.delete(id);
	}
}
