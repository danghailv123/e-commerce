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
public class ReceiptDto {
    private Integer id;
    private Integer userId;
    @JsonProperty("create_date")
    private Timestamp createDate;
    private String name;
    @JsonProperty("phone_number")
    private String phoneNumber;

    private String address;

    private Integer status;
}
