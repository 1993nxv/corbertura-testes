package br.com.cobertura_testes.api.service.impl;

import br.com.cobertura_testes.api.domain.User;
import br.com.cobertura_testes.api.repository.UserRepository;
import br.com.cobertura_testes.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

}
