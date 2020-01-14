package com.codegym.service.impl;

import com.codegym.model.Picture;
import com.codegym.repository.PictureRepository;
import com.codegym.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class PictureServiceImpl implements PictureService {
    @Autowired
    private PictureRepository pictureRepository;
    @Override
    public Iterable<Picture> findAllPicture() {
        return pictureRepository.findAll();
    }

    @Override
    public Optional<Picture> findByPictureId(Long id) {
        return pictureRepository.findById(id);
    }

    @Override
    public void savePicture(Picture picture) {
        pictureRepository.save(picture);
    }

    @Override
    public void deletePicture(Long id) {
        pictureRepository.deleteById(id);
    }
}