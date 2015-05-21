package com.kb.service.inquiry;

import com.kb.domain.Inquiry;
import com.kb.repository.InquiryRepository;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 * Created by romanmudryi on 21.05.15.
 */
@Component
public class DefaultInquiryService implements InquiryService {
    @Inject
    InquiryRepository inquiryRepository;

    @Override
    @Transactional
    public Inquiry updateLast(Long id) {
        Inquiry inquiry = inquiryRepository.findOne(id);
        inquiry.setSeenDate(new DateTime());
        return inquiryRepository.save(inquiry);
    }
}
