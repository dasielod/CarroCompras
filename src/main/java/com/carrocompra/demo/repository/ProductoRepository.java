package com.carrocompra.demo.repository;

import com.carrocompra.demo.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Product entity.
 */
@Repository
public interface ProductoRepository extends JpaRepository<Product, Long> {

}
