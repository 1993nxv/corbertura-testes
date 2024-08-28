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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

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
    @DisplayName("whenFindByIdThenReturnSuccess")
    void findById() {
        when(userService.findById(anyLong())).thenReturn(user);
        when(modelMapper.map(any(), any())).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userContoller.findById(ID);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserDTO.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NOME, response.getBody().getNome());
        assertEquals(EMAIL, response.getBody().getEmail());
        assertEquals(PASSWORD, response.getBody().getPassword());
    }

    @Test
    @DisplayName("whenFindAllThenReturnAnListOfUserDTO")
    void findAll() {
        when(userService.findAll()).thenReturn(List.of(user));
        when(modelMapper.map(any(), any())).thenReturn(userDTO);

        ResponseEntity<List<UserDTO>> response = userContoller.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(UserDTO.class, response.getBody().get(INDEX).getClass());

        assertEquals(ID, response.getBody().get(INDEX).getId());
        assertEquals(NOME, response.getBody().get(INDEX).getNome());
        assertEquals(EMAIL, response.getBody().get(INDEX).getEmail());
        assertEquals(PASSWORD, response.getBody().get(INDEX).getPassword());
    }

    @Test
    @DisplayName("whenSaveThenReturnCreated")
    void save() {
        when(userService.save(any())).thenReturn(user);

        ResponseEntity<UserDTO> response = userContoller.save(userDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
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