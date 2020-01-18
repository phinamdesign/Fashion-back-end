//package com.codegym.service.impl;
//
//import com.codegym.model.OderDetail;
//import com.codegym.repository.OderDetailRepository;
//import com.codegym.service.OderDetailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class OderDetaiServiceImpl implements OderDetailService {
//    @Autowired
//    private OderDetailRepository oderDetailRepository;
//
//    @Override
//    public Optional<OderDetail> findById(Long id) {
//        return oderDetailRepository.findById(id);
//    }
//
//    @Override
//    public Iterable<OderDetail> findAll() {
//        return oderDetailRepository.findAll();
//    }
//
//    @Override
//    public OderDetail save(OderDetail oderDetail) {
//        return oderDetailRepository.save(oderDetail);
//    }
//
//    @Override
//    public void delete(Long id) {
//        oderDetailRepository.deleteById(id);
//    }
//}
