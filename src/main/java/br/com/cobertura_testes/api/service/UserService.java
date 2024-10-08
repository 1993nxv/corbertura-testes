package br.com.cobertura_testes.api.service;

import br.com.cobertura_testes.api.domain.User;

import java.util.List;

public interface UserService {

    User findById(Long id);

    List<User> findAll();

    User save(User user);

    User update(User user);

    void deleteById(Long id);
}
