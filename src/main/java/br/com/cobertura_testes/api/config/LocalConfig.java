package br.com.cobertura_testes.api.config;

import br.com.cobertura_testes.api.domain.User;
import br.com.cobertura_testes.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UserRepository  userRepository;

    @Bean
    public List<User> startDB(){
        User u1 = new User(1L, "Delmondes", "del@mail.com", "123");
        User u2 = new User(2L, "Bruna", "bru@mail.com", "123");
        User u3 = new User(3L, "Sandra", "sandel@mail.com", "123");

        return userRepository.saveAll(List.of(u1, u2, u3));
    }
}
