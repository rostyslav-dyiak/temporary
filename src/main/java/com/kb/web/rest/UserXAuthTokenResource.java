package com.kb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kb.security.xauth.Token;
import com.kb.security.xauth.TokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * Created by rdyyak on 24.04.15.
 */
@RestController
@RequestMapping("/api")
public class UserXAuthTokenResource {
    private final Logger log = LoggerFactory.getLogger(UserXAuthTokenResource.class);

    @Inject
    private TokenProvider tokenProvider;

    @Inject
    private AuthenticationManager authenticationManager;

    @Inject
    private UserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate",
        method = RequestMethod.POST)
    @Timed
    public Token authorize(@RequestParam final String email, @RequestParam final String password) {
    	log.info("Authenticating user:", email);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = this.authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails details = this.userDetailsService.loadUserByUsername(email);
        return tokenProvider.createToken(details);
    }
}
