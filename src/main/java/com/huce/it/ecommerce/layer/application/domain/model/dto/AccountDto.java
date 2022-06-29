package com.huce.it.ecommerce.layer.application.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.sql.Timestamp;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AccountDto {
    @JsonProperty("user_id")
    private Integer userId;

    private String name;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private String password;

    private String email;

    private Integer status;

    private String role;

    @JsonProperty("create_date")
    private Timestamp createDate;
}
