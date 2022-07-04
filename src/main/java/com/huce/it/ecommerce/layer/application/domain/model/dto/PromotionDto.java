package com.huce.it.ecommerce.layer.application.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.huce.it.ecommerce.layer.application.domain.constant.SearchFields;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromotionDto {
    private Integer id;

    private String name;

    private Float percent;

    @JsonProperty("from_date")
    private Timestamp fromDate;

    @JsonProperty("to_date")
    private Timestamp toDate;

    private String description;

    private String image;

    private Integer status;
    private String entry = SearchFields.PROMOTION;
}
