package br.com.cobertura_testes.api.service.impl;

import br.com.cobertura_testes.api.domain.User;
import br.com.cobertura_testes.api.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User findById(Long id) {
        return null;
    }

}
