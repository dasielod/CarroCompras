package com.carrocompra.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "detalleVenta"
)
public class SaleDetail {

    @Id
    @Column(name = "idDetalleVenta")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JsonIgnoreProperties("sale")
    private Sale sale;

    @ManyToOne(optional = false)
    @JsonIgnoreProperties("saleDetails")
    private Product product;
}
