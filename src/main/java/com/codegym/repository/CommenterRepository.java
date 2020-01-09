package com.codegym.repository;

import com.codegym.model.Commenter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommenterRepository extends JpaRepository<Commenter, Long> {
    Iterable<Commenter> findByTitle(String title);
}
