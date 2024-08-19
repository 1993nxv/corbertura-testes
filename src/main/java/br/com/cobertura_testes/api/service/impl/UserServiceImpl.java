package br.com.cobertura_testes.api.service.impl;

import br.com.cobertura_testes.api.domain.User;
import br.com.cobertura_testes.api.repository.UserRepository;
import br.com.cobertura_testes.api.service.UserService;
import br.com.cobertura_testes.api.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

}
