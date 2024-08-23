package br.com.cobertura_testes.api.service.impl;

import br.com.cobertura_testes.api.domain.User;
import br.com.cobertura_testes.api.domain.dto.UserDTO;
import br.com.cobertura_testes.api.repository.UserRepository;
import br.com.cobertura_testes.api.service.exception.EmailEmUsoException;
import br.com.cobertura_testes.api.service.exception.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class  UserServiceImplTest {

    public static final long ID         = 1L;
    public static final String NOME     = "Delmondes";
    public static final String EMAIL    = "del@mail.com";
    public static final String PASSWORD = "123";
    public static final int INDEX       = 0;
    public static final String USUARIO_NAO_ENCONTRADO_MSG = "Usuário não encontrado";

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ModelMapper modelMapper;

    private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUsers();
    }

    @Test
    @DisplayName("whenFindByIdThenReturnAnUserInstance")
    void findById() {
        Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(optionalUser);

        User response = userService.findById(ID);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(User.class, response.getClass());
        Assertions.assertEquals(ID, response.getId());
        Assertions.assertEquals(NOME, response.getNome());
        Assertions.assertEquals(EMAIL, response.getEmail());
    }

    @Test
    @DisplayName("whenFindByIdThenReturnAnObjectNotFoundException")
    void exceptionFindById() {
        Mockito.when(userRepository.findById(Mockito.anyLong()))
                .thenThrow(new ObjectNotFoundException(USUARIO_NAO_ENCONTRADO_MSG));
        try {
            userService.findById(ID);
        }catch (Exception exception) {
            Assertions.assertEquals(ObjectNotFoundException.class, exception.getClass());
            Assertions.assertEquals(USUARIO_NAO_ENCONTRADO_MSG, exception.getMessage());
        }
    }

    @Test
    @DisplayName("whenFindAllThenReturnAnListOfUser")
    void findAll() {
        Mockito.when(userRepository.findAll()).thenReturn(List.of(user));
        List<User> response = userService.findAll();
        Assertions.assertNotNull(response);
        Assertions.assertEquals(1, response.size());
        Assertions.assertEquals(User.class, response.get(INDEX).getClass());

        Assertions.assertEquals(ID, response.get(INDEX).getId());
        Assertions.assertEquals(NOME, response.get(INDEX).getNome());
        Assertions.assertEquals(EMAIL, response.get(INDEX).getEmail());
    }

    @Test
    @DisplayName("whenCreateThenReturnSuccess")
    void save() {
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);

        User response = userService.save(user);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(User.class, response.getClass());
        Assertions.assertEquals(ID, response.getId());
        Assertions.assertEquals(NOME, response.getNome());
        Assertions.assertEquals(EMAIL, response.getEmail());
        Assertions.assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    @DisplayName("whenCreateThenReturnAnDataIntegrityViolation")
    void exceptionSave() {
        Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(optionalUser);
        try{
            optionalUser.get().setId(2L);
            userService.save(user);
        }catch (Exception exception) {
            Assertions.assertEquals(EmailEmUsoException.class, exception.getClass());
            Assertions.assertEquals("E-mail já cadastrado", exception.getMessage());
        }
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void verificaEmail() {
    }

    private void startUsers() {
        this.user = new User(ID, NOME, EMAIL, PASSWORD);
        this.userDTO = new UserDTO(ID, NOME, EMAIL, PASSWORD);
        this.optionalUser = Optional.of(new User(ID, NOME, EMAIL, PASSWORD));
    }
}