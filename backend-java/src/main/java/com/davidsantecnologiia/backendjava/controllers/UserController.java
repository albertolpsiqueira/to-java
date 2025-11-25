package com.davidsantecnologiia.backendjava.controllers;

import com.davidsantecnologiia.backendjava.entities.UserEntity;
import com.davidsantecnologiia.backendjava.services.UserService;
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
    public ResponseEntity<UserEntity> create(@RequestBody UserEntity userEntity){
        return ResponseEntity.ok(userService.create(userEntity));
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> findOne(@PathVariable String id){
        return ResponseEntity.ok(userService.findOne(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> update(@RequestBody UserEntity userEntity, @PathVariable String id){
        return ResponseEntity.ok(userService.update(userEntity, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable("id") String id){
        userService.remove(id);
        return ResponseEntity.noContent().build();
    }
}
