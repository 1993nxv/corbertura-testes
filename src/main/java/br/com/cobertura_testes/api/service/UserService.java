package br.com.cobertura_testes.api.service;

import br.com.cobertura_testes.api.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User findById(Long id);

}
