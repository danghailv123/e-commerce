package com.huce.it.ecommerce.layer.application.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
//đánh giá
public class EvaluateDto {
    private Integer id;

    @JsonProperty("user-id")
    private Integer userId;

    @JsonProperty("pro_id")
    private Integer proId;

    private Integer rate;

    private String comment;

    @JsonProperty("create_date")
    private Timestamp createDate;
}
