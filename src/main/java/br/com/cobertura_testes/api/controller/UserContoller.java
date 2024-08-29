package br.com.cobertura_testes.api.controller;

import br.com.cobertura_testes.api.domain.User;
import br.com.cobertura_testes.api.domain.dto.UserDTO;
import br.com.cobertura_testes.api.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserContoller {

    public static final String ID = "/{id}";

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping(ID)
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

    @PostMapping
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO userDTO){
        User user = mapper.map(userDTO, User.class);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(ID)
                .buildAndExpand(userService.save(user).getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(ID)
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO userDTO){
        userDTO.setId(id);
        User user = mapper.map(userDTO, User.class);
        return ResponseEntity.ok().body(mapper.map(userService.update(user), UserDTO.class));
    }

    @DeleteMapping(ID)
    public ResponseEntity<UserDTO> deleteById(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
