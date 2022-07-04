package com.huce.it.ecommerce.layer.application.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.huce.it.ecommerce.layer.application.domain.constant.SearchFields;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProductTypeDto {
    private Integer id;
    private Integer pg_id;
    private String pg_name;
    private String name;
    private String description;
    private Integer status;
    private String entry = SearchFields.PRODUCT_TYPE;
}
