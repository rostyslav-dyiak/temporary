package com.kb.repository;

import com.kb.domain.SystemAnnouncement;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the SystemAnnouncement entity.
 */
public interface SystemAnnouncementRepository extends JpaRepository<SystemAnnouncement,Long> {

}
