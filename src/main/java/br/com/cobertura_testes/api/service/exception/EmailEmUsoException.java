package br.com.cobertura_testes.api.service.exception;

public class EmailEmUsoException extends RuntimeException {

    public EmailEmUsoException(String message) {
        super(message);
    }
}
