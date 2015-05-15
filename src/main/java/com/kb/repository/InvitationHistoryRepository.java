/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.kb.repository;

import com.kb.domain.Company;
import com.kb.domain.InvitationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by rdyyak on 14.05.15.
 */
public interface InvitationHistoryRepository extends JpaRepository<InvitationHistory, Long> {
    public List<InvitationHistory> findAllByCompany(Company company);
}
