package com.davidsantecnologiia.backendjava.services;

import com.davidsantecnologiia.backendjava.dtos.UserRequestDTO;
import com.davidsantecnologiia.backendjava.dtos.UserResponseDTO;
import com.davidsantecnologiia.backendjava.entities.UserEntity;
import com.davidsantecnologiia.backendjava.exceptions.DuplicateEmailException;
import com.davidsantecnologiia.backendjava.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private void checkEmailUniqueness(String email){
        Optional<UserEntity> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent()){
            throw new DuplicateEmailException("O email '"+email+"' já está sendo usado em outro cadastro.");
        }
    }

    public UserResponseDTO create(UserRequestDTO userRequestDTO){
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userRequestDTO.name());
        userEntity.setEmail(userRequestDTO.email());
        UserEntity userEntitySaved = userRepository.save(userEntity);
        return new UserResponseDTO(userEntitySaved.getId(), userEntitySaved.getName(), userEntitySaved.getEmail());
    }

    public List<UserResponseDTO> findAll(){
        List<UserEntity> userEntityList = userRepository.findAll();
        return userEntityList.stream().map(userEntity -> new UserResponseDTO(userEntity.getId(), userEntity.getName(), userEntity.getEmail())).collect(Collectors.toList());
    }

    public UserResponseDTO findOne(String id){
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário com ID: "+id+" não encontrado"));
        return new UserResponseDTO(userEntity.getId(), userEntity.getName(), userEntity.getEmail());
    }

    public UserResponseDTO update(UserRequestDTO userRequestDTO, String id){
        UserEntity userToUpdate = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário com ID "+id+" não encontrado"));
        userToUpdate.setName(userRequestDTO.name());
        userToUpdate.setEmail(userRequestDTO.email());
        UserEntity userUpdated = userRepository.save(userToUpdate);
        return new UserResponseDTO(userUpdated.getId(), userUpdated.getName(), userUpdated.getEmail());
    }

    public void remove(String id){
        if (!userRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário com ID: "+id+" não encontrado");
        }
        userRepository.deleteById(id);
    }

}

