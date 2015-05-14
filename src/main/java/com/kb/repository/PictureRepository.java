package com.kb.repository;

import com.kb.domain.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Picture entity.
 */
public interface PictureRepository extends JpaRepository<Picture,Long> {

}
