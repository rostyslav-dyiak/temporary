package com.kb.repository;

import com.kb.domain.Eatery_member;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Eatery_member entity.
 */
public interface Eatery_memberRepository extends JpaRepository<Eatery_member,Long> {

}
