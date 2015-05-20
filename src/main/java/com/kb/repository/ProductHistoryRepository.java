package com.kb.repository;

import com.kb.domain.ProductHistory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by rdyyak on 20.05.15.
 */
public interface ProductHistoryRepository extends JpaRepository<ProductHistory, Long> {
}
