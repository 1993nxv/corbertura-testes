package br.com.cobertura_testes.api.controller;

import br.com.cobertura_testes.api.domain.dto.UserDTO;
import br.com.cobertura_testes.api.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserContoller {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok()
                .body(mapper.map(userService.findById(id), UserDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        List<UserDTO> usersDTO = userService.findAll()
                .stream()
                .map(user -> mapper.map(user, UserDTO.class))
                .collect(Collectors.toList()
        );
        return ResponseEntity.ok().body(usersDTO);
    }
}
