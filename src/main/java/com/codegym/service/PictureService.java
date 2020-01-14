package com.codegym.service;

import com.codegym.model.Picture;

import java.util.Optional;

public interface PictureService {
    Iterable<Picture> findAllPicture();
    Optional<Picture> findByPictureId(Long id);
    void savePicture(Picture picture);
    void deletePicture(Long id);
}
