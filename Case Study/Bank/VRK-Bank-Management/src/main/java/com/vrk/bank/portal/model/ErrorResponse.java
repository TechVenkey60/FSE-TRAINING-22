package com.vrk.bank.portal.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ErrorResponse {
    private Integer code;
    private String message;
}
