package com.codegym.service;
import com.codegym.model.Supplier;
import java.util.List;
import java.util.Optional;

public interface SupplierService {
    List<Supplier> findAllSupplier();
    Optional<Supplier> findSupplierById(Long id);
    Supplier saveSupplier(Supplier supplier);
    void removeSupplier(Long id);
}
