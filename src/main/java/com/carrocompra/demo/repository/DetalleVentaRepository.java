package com.carrocompra.demo.repository;

import com.carrocompra.demo.domain.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Sale entity.
 */
@Repository
public interface DetalleVentaRepository extends JpaRepository<SaleDetail, Long> {

}
