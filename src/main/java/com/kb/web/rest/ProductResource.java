package com.kb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kb.domain.Product;
import com.kb.domain.User;
import com.kb.repository.ProductRepository;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Product.
 */
@RestController
@RequestMapping("/api")
public class ProductResource {

    private final Logger log = LoggerFactory.getLogger(ProductResource.class);

    @Inject
    private ProductRepository productRepository;
    @Inject
    private UserRepository userRepository;

    /**
     * POST  /products -> Create a new product.
     */
    @RequestMapping(value = "/products",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody Product product) throws URISyntaxException {
        log.debug("REST request to save Product : {}", product);
        if (product.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new product cannot already have an ID").build();
        }
        productRepository.save(product);
        return ResponseEntity.created(new URI("/api/products/" + product.getId())).build();
    }

    /**
     * PUT  /products -> Updates an existing product.
     */
    @RequestMapping(value = "/products",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody Product product) throws URISyntaxException {
        log.debug("REST request to update Product : {}", product);
        if (product.getId() == null) {
            return create(product);
        }
        productRepository.save(product);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /products -> get all the products.
     */
    @RequestMapping(value = "/products",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Product>> getAllPaged(@RequestParam(value = "page", required = false) final Integer offset,
                                @RequestParam(value = "per_page", required = false) final Integer limit) throws URISyntaxException {
        Optional<User> user = userRepository.findOneByEmail(SecurityUtils.getCurrentLogin());
        if(user.isPresent()) {
            log.debug("REST request to get all Products");
            Page<Product> page = productRepository.findByCompany(user.get().getCompany(), PaginationUtil.generatePageRequest(offset, limit));
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/products", offset, limit);
            return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * GET  /products -> get all the products.
     */
    @RequestMapping(value = "/products/uncategorized",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional
    public ResponseEntity<List<Product>> getAll() throws URISyntaxException {
        Optional<User> user = userRepository.findOneByEmail(SecurityUtils.getCurrentLogin());
        if(user.isPresent()) {
            log.debug("REST request to get all Products");
            List<Product> products = productRepository.findByCompanyAndCategoryIsNull(user.get().getCompany());
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * GET  /products/:id -> get the "id" product.
     */
    @RequestMapping(value = "/products/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Product> get(@PathVariable Long id) {
        log.debug("REST request to get Product : {}", id);
        return Optional.ofNullable(productRepository.findOne(id))
            .map(product -> new ResponseEntity<>(
                product,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /products/:id -> delete the "id" product.
     */
    @RequestMapping(value = "/products/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Product : {}", id);
        productRepository.delete(id);
    }
}
