package org.ntnu.rest.repository;

import org.ntnu.rest.entity.Calculation;
import org.ntnu.rest.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculationRepository extends JpaRepository<Calculation, Integer> {
    Page<Calculation> findByUserOrderByIdDesc(User user, Pageable pageable);
}
