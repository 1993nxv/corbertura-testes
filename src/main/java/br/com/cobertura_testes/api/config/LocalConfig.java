package br.com.cobertura_testes.api.config;

import br.com.cobertura_testes.api.domain.User;
import br.com.cobertura_testes.api.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UserRepository  userRepository;

    @PostConstruct
    public void startDB(){
        User u1 = new User(null, "Delmondes", "del@mail.com", "123");
        User u2 = new User(null, "Bruna", "bru@mail.com", "123");
        User u3 = new User(null, "Sandra", "sandel@mail.com", "123");

        userRepository.saveAll(List.of(u1, u2, u3));
    }
}
