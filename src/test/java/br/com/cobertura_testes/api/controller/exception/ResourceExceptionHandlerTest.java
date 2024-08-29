package br.com.cobertura_testes.api.controller.exception;

import br.com.cobertura_testes.api.service.exception.EmailEmUsoException;
import br.com.cobertura_testes.api.service.exception.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ResourceExceptionHandlerTest {

    @InjectMocks
    private ResourceExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("whenObjctNotFoundExceptionThenReturnResponseEntity")
    void objectNotFound() {
        ResponseEntity<StandardError> response = exceptionHandler.objectNotFound(
                new ObjectNotFoundException("Objeto não encontrado"),
                new MockHttpServletRequest()
        );

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals("Objeto não encontrado", response.getBody().getError());
        assertEquals(404, response.getBody().getStatus());
    }

    @Test
    @DisplayName("whenEmailEmUsoExceptionThenReturnResponseEntity")
    void emailEmUsoException() {
        ResponseEntity<StandardError> response = exceptionHandler.emailEmUsoException(
                new EmailEmUsoException("E-mail já cadastrado"),
                new MockHttpServletRequest()
        );

        assertNotNull(response);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals("E-mail já cadastrado", response.getBody().getError());
        assertEquals(409, response.getBody().getStatus());
    }
}