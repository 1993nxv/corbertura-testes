package br.com.cobertura_testes.api.controller;

import br.com.cobertura_testes.api.domain.User;
import br.com.cobertura_testes.api.domain.dto.UserDTO;
import br.com.cobertura_testes.api.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserContollerTest {

    public static final long ID         = 1L;
    public static final String NOME     = "Delmondes";
    public static final String EMAIL    = "del@mail.com";
    public static final String PASSWORD = "123";
    public static final int INDEX       = 0;

    @InjectMocks
    private UserContoller userContoller;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private UserServiceImpl userService;

    private User user;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUsers();
    }

    @Test
    @DisplayName("whenFindByIdThenReturnAnUserDTOInstance")
    void findById() {
        
    }

    @Test
    void findAll() {
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }

    private void startUsers() {
        this.user = new User(ID, NOME, EMAIL, PASSWORD);
        this.userDTO = new UserDTO(ID, NOME, EMAIL, PASSWORD);
    }
}