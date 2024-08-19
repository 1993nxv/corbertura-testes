package br.com.cobertura_testes.api.controller;

import br.com.cobertura_testes.api.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserContoller {

    public ResponseEntity<User> findById(@PathVariable Long id){
        return null;
    }
}
