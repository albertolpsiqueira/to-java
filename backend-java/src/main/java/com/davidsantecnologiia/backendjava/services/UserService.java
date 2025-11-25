package com.davidsantecnologiia.backendjava.services;

import com.davidsantecnologiia.backendjava.entities.UserEntity;
import com.davidsantecnologiia.backendjava.exceptions.DuplicateEmailException;
import com.davidsantecnologiia.backendjava.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    private void checkEmailUniqueness(String email){
        Optional<UserEntity> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent()){
            throw new DuplicateEmailException("O email '"+email+"' já está sendo usado em outro cadastro.");
        }
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity create(UserEntity userEntity){
        checkEmailUniqueness(userEntity.getEmail());
        return userRepository.save(userEntity);
    }

    public List<UserEntity> findAll(){
        return userRepository.findAll();
    }

    public UserEntity findOne(String id){
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário com ID: "+id+" não encontrado"));
    }

    public UserEntity update(UserEntity userEntity, String id){
        UserEntity userToUpdate = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário com EMAIL "+id+" não encontrado"));
        userToUpdate.setName(userEntity.getName());
        userToUpdate.setEmail(userEntity.getEmail());
        return userRepository.save(userToUpdate);
    }

    public void remove(String id){
        userRepository.deleteById(id);
    }

}

