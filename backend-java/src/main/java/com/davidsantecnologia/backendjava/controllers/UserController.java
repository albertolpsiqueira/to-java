package com.davidsantecnologia.backendjava.controllers;

import com.davidsantecnologia.backendjava.dtos.UserRequestDTO;
import com.davidsantecnologia.backendjava.dtos.UserResponseDTO;
import com.davidsantecnologia.backendjava.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:8006")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody UserRequestDTO userRequestDTO){
        UserResponseDTO userResponseDTO = userService.create(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll(){
        List<UserResponseDTO> userResponseDTOList = userService.findAll();
        return ResponseEntity.ok(userResponseDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findOne(@PathVariable String id){
        UserResponseDTO userResponseDTO = userService.findOne(id);
        return ResponseEntity.ok(userResponseDTO);
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(@RequestBody UserRequestDTO userRequestDTO, @PathVariable String id){
        UserResponseDTO userResponseDTO = userService.update(userRequestDTO, id);
        return ResponseEntity.ok(userResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable("id") String id){
        userService.remove(id);
        return ResponseEntity.noContent().build();
    }
}
