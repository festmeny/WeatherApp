package com.koncsikn.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResponse {
    @JsonProperty("cod")
    private String code;
    private String message;

    public String toString() {
        return "Code: " + code + ", message: " + message;
    }
}
