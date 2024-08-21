package br.com.cobertura_testes.api.service.impl;

import br.com.cobertura_testes.api.domain.User;
import br.com.cobertura_testes.api.repository.UserRepository;
import br.com.cobertura_testes.api.service.UserService;
import br.com.cobertura_testes.api.service.exception.EmailEmUsoException;
import br.com.cobertura_testes.api.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public User save(User user) {
        verificaEmail(user);
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        findById(user.getId());
        return save(user);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        userRepository.deleteById(id);
    }

    public void verificaEmail(User user) {
        Optional<User> userByEmail = userRepository.findByEmail(user.getEmail());
        if(userByEmail.isPresent() && !userByEmail.get().getId().equals(user.getId())) {
            throw new EmailEmUsoException("E-mail já cadastrado");
        }
    }

}
