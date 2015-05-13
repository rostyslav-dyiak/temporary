package com.kb.repository;

import java.util.List;
import java.util.Optional;

import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kb.domain.Company;
import com.kb.domain.User;

/**
 * Spring Data JPA repository for the User entity.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByActivationKey(String activationKey);

    List<User> findAllByActivatedIsFalseAndCreatedDateBefore(DateTime dateTime);

    Page<User> findByCompany(Company company, Pageable page);

    Optional<User> findOneByEmail(String email);

    Optional<User> findOneByLogin(String login);

    @Override
	void delete(User t);

}
