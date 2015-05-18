package com.kb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kb.domain.Product;
import com.kb.domain.User;
import com.kb.repository.UserRepository;
import com.kb.search.model.ProductSearch;
import com.kb.security.SecurityUtils;
import com.kb.service.product.ProductService;
import com.kb.web.rest.dto.product.ProductDto;
import com.kb.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
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
    private UserRepository userRepository;

    @Inject
    private ProductService productService;

    @RequestMapping(value = "/products",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody final Product product) throws URISyntaxException {
        log.debug("REST request to save Product : {}", product);
        if (product.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new product cannot already have an ID").build();
        }
        productService.save(product);
        return ResponseEntity.created(new URI("/api/products/" + product.getId())).build();
    }

    @RequestMapping(value = "/products",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody final Product product) throws URISyntaxException {
        log.debug("REST request to update Product : {}", product);
        if (product.getId() == null) {
            return create(product);
        }
        productService.save(product);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/products/uncategorized",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional
    public ResponseEntity<List<Product>> getAll() throws URISyntaxException {
        Optional<User> user = userRepository.findOneByEmail(SecurityUtils.getCurrentLogin());
        if(user.isPresent()) {
            log.debug("REST request to get all Products");
            List<Product> products = productService.findByCompanyAndSubSubCategoryIsNull(user.get().getCompany());
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/products",
    		method = RequestMethod.GET,
    		produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<ProductDto>> getAllWithSearch(@RequestParam(value = "page" , required = false) final Integer offset,
            @RequestParam(value = "per_page", required = false) final Integer limit,
            @RequestParam(value = "search", defaultValue = "false") final Boolean applySearch,
            final ProductSearch searchDto) {
        Optional<User> user = userRepository.findOneByEmail(SecurityUtils.getCurrentLogin());
        if(user.isPresent()) {
        	log.debug("REST request to get all Products");
        	Pageable pageable = PaginationUtil.generatePageRequest(offset, limit);
        	List<ProductDto> products = productService.findAll(pageable, searchDto, applySearch);
        	return new ResponseEntity<>(products, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    };

    @RequestMapping(value = "/products/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Product> get(@PathVariable final Long id) {
        log.debug("REST request to get Product : {}", id);
        return Optional.ofNullable(productService.findOne(id))
            .map(product -> new ResponseEntity<>(
                product,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/products/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable final Long id) {
        log.debug("REST request to delete Product : {}", id);
        productService.delete(id);
    }
}
