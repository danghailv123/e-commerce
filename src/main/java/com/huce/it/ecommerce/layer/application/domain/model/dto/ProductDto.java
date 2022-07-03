package com.huce.it.ecommerce.layer.application.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@AllArgsConstructor
public class ProductDto {
    private Integer id;

    @JsonProperty("tra_id")
    private Integer traId;

    @JsonProperty("trademark_name")
    private String trademarkName ;

    @JsonProperty("prom_id")
    private Integer promId;

    @JsonProperty("promotion_name")
    private String promotionName;

    @JsonProperty("pt_id")
    private Integer ptId;

    @JsonProperty("name_type")
    private String nameType;

    @JsonProperty("group_name")
    private String groupName;

    private String name;

    private Float price;

    private Integer quantity;

    private String image;

    @JsonProperty("n_rate")
    private Float nRate;

    private String description;

    private Integer status;
}
