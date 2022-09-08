package com.heroes.clientes.config.exception.model;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class ErrorModel implements Serializable {
    int statusCode;
    String userMessage;
    String detailMessage;
}
