package com.kb.repository;

import com.kb.domain.EateryMember;
import org.springframework.data.jpa.repository.*;

/**
 * Spring Data JPA repository for the Eatery_member entity.
 */
public interface EateryMemberRepository extends JpaRepository<EateryMember,Long> {

}
