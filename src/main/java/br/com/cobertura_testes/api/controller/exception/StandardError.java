package br.com.cobertura_testes.api.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class StandardError {

    private LocalDateTime timeStamp;
    private Integer status;
    private String error;
    private String path;

}
