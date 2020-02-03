package com.codegym.repository;

import com.codegym.model.Order;
import com.codegym.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findById(Long id);
    Order findByStatusAndUser_Id(Status status, Long User_Id);
//    @Query(value = "select product_detail_id from  orders join users on orders.user_id = users.id join orders_product_detail on orders.id = orders_product-detail.order_id where status = 'normal' and user_id = ?1", nativeQuery = true)
    List<Order> findAllByUser_Id(Long User_Id);
}
