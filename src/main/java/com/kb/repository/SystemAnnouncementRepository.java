package com.kb.repository;

import com.kb.domain.SystemAnnouncement;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the SystemAnnouncement entity.
 */
public interface SystemAnnouncementRepository extends JpaRepository<SystemAnnouncement,Long> {

}
