package com.codegym.repository;

import com.codegym.model.Oder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OderRepository extends JpaRepository<Oder, Long> {
    Optional<Oder> findById(Long id);
}
