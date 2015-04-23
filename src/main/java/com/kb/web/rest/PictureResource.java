package com.kb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kb.domain.Picture;
import com.kb.repository.PictureRepository;
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
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Picture.
 */
@RestController
@RequestMapping("/api")
public class PictureResource {

    private final Logger log = LoggerFactory.getLogger(PictureResource.class);

    @Inject
    private PictureRepository pictureRepository;

    /**
     * POST  /pictures -> Create a new picture.
     */
    @RequestMapping(value = "/pictures",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@Valid @RequestBody Picture picture) throws URISyntaxException {
        log.debug("REST request to save Picture : {}", picture);
        if (picture.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new picture cannot already have an ID").build();
        }
        pictureRepository.save(picture);
        return ResponseEntity.created(new URI("/api/pictures/" + picture.getId())).build();
    }

    /**
     * PUT  /pictures -> Updates an existing picture.
     */
    @RequestMapping(value = "/pictures",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@Valid @RequestBody Picture picture) throws URISyntaxException {
        log.debug("REST request to update Picture : {}", picture);
        if (picture.getId() == null) {
            return create(picture);
        }
        pictureRepository.save(picture);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /pictures -> get all the pictures.
     */
    @RequestMapping(value = "/pictures",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Picture> getAll() {
        log.debug("REST request to get all Pictures");
        return pictureRepository.findAll();
    }

    /**
     * GET  /pictures/:id -> get the "id" picture.
     */
    @RequestMapping(value = "/pictures/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Picture> get(@PathVariable Long id) {
        log.debug("REST request to get Picture : {}", id);
        return Optional.ofNullable(pictureRepository.findOne(id))
            .map(picture -> new ResponseEntity<>(
                picture,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /pictures/:id -> delete the "id" picture.
     */
    @RequestMapping(value = "/pictures/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Picture : {}", id);
        pictureRepository.delete(id);
    }
}
