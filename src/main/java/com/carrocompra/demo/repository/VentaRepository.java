package com.carrocompra.demo.repository;

import com.carrocompra.demo.domain.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Sale entity.
 */
@Repository
public interface VentaRepository extends JpaRepository<Sale, Long> {

    List<Sale> findAllByClient_Id( Long id);
}
