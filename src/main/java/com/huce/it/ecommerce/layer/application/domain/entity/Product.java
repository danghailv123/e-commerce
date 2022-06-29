package com.huce.it.ecommerce.layer.application.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Entity(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pro_id")
    private Integer id;

    @Column(name = "tra_id")
    private Integer traId;

    @Column(name = "prom_id")
    private Integer promId;

    @Column(name = "pt_id")
    private Integer ptId;

    private String name;

    private Float price;

    private Integer quantity;

    private String image;

    @Column(name = "n_rate")
    private Float nRate;

    private String description;

    private Integer status;
}
