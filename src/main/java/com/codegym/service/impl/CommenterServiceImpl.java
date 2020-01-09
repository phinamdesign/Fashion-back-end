package com.codegym.service.impl;

import com.codegym.model.Commenter;
import com.codegym.repository.CommenterRepository;
import com.codegym.service.CommenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommenterServiceImpl implements CommenterService {
    @Autowired
    private CommenterRepository commenterRepository;

    @Override
    public Optional<Commenter> findById(Long id) {
        return commenterRepository.findById(id);
    }

    @Override
    public Iterable<Commenter> findAll() {
        return commenterRepository.findAll();
    }

    @Override
    public Commenter save(Commenter commenter) {
        return commenterRepository.save(commenter);
    }

    @Override
    public void delete(Long id) {
        commenterRepository.deleteById(id);
    }

    @Override
    public Iterable<Commenter> findByTitle(String title) {
        return commenterRepository.findByTitle(title);
    }
}
