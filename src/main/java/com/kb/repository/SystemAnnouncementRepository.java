package com.kb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kb.domain.SystemAnnouncement;

/**
 * Spring Data JPA repository for the SystemAnnouncement entity.
 */
public interface SystemAnnouncementRepository extends JpaRepository<SystemAnnouncement,Long> {

}
