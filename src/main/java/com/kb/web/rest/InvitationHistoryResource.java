
package com.kb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kb.domain.Company;
import com.kb.domain.InvitationHistory;
import com.kb.repository.CompanyRepository;
import com.kb.repository.InvitationHistoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by rdyyak on 14.05.15.
 */
@RestController
@RequestMapping("/api")
public class InvitationHistoryResource {

    private final Logger log = LoggerFactory.getLogger(InvitationHistoryResource.class);

    @Inject
    private InvitationHistoryRepository invitationHistoryRepository;
    @Inject
    private CompanyRepository companyRepository;

    /**
     * GET  /invitations -> get all the invitations by company.
     */
    @RequestMapping(value = "/invitations/company/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<InvitationHistory> getAllByCompany(@PathVariable Long id) {
        Company company = companyRepository.findOne(id);
        log.debug("REST request to get all Invitation Histories by company : {}", company);
        return invitationHistoryRepository.findAllByCompany(company);
    }
}
