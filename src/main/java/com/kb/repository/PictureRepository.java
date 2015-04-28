package com.kb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kb.domain.Picture;

/**
 * Spring Data JPA repository for the Picture entity.
 */
public interface PictureRepository extends JpaRepository<Picture,Long> {

}
