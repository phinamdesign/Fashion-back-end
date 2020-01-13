package com.codegym.repository;

import com.codegym.model.Commenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommenterRepository extends JpaRepository<Commenter, Long> {
    Iterable<Commenter> findByTitle(String title);
}
