package com.shoppingcar.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

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
