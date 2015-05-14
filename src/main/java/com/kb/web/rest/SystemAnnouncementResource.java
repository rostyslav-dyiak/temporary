package com.kb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kb.service.announcement.SystemAnnouncementService;
import com.kb.web.rest.dto.announcement.SystemAnnouncementDto;
import com.kb.web.rest.dto.announcement.SystemAnnouncementResponseDto;
import com.kb.web.rest.dto.announcement.SystemAnnouncementsDto;
import com.kb.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

/**
 * REST controller for managing SystemAnnouncement.
 */
@RestController
@RequestMapping("/api")
public class SystemAnnouncementResource {
	private final Logger log = LoggerFactory.getLogger(SystemAnnouncementResource.class);

	@Inject
	private SystemAnnouncementService service;

    @RequestMapping(value = "/messages", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
	public ResponseEntity<Void> create(@Valid @RequestBody final SystemAnnouncementDto dto) throws URISyntaxException {

        log.debug("REST request to save message : {}", dto);

        if (dto.getId() != null) {
            return ResponseEntity
					.badRequest()
					.header("Failure", "A new message cannot already have an ID")
					.build();
		}

        service.save(dto);
        return ResponseEntity.created(new URI("/api/messages/" + dto.getId())).build();
	}

	@RequestMapping(value = "/messages", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<Void> update(
			@Valid @RequestBody final SystemAnnouncementDto dto)
			throws URISyntaxException {
		log.debug("REST request to update message : {}", dto);
		if (dto.getId() == null) {
			return create(dto);
		}
		service.save(dto);
		return ResponseEntity.ok().build();
	}

	@RequestMapping(value = "/messages", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<SystemAnnouncementsDto> getAll(
			@RequestParam(value = "page", required = false) final Integer offset,
			@RequestParam(value = "per_page", required = false) final Integer limit)
			throws URISyntaxException {
		SystemAnnouncementsDto announcements = service.findAll(PaginationUtil.generatePageRequest(offset, limit));
		return new ResponseEntity<>(announcements, HttpStatus.OK);
	}

	@RequestMapping(value = "/messages/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<SystemAnnouncementResponseDto> get(@PathVariable final Long id) {
		log.debug("REST request to get message : {}", id);
		return Optional
				.ofNullable(service.findOne(id))
				.map(systemAnnouncement -> new ResponseEntity<>(
						systemAnnouncement, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@RequestMapping(value = "/messages/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public void delete(@PathVariable final Long id) {
		log.debug("REST request to delete message : {}", id);
		service.delete(id);
	}
}
