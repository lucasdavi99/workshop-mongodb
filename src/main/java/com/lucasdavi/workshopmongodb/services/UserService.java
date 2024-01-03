package com.lucasdavi.workshopmongodb.services;

import com.lucasdavi.workshopmongodb.dtos.UserDTO;
import com.lucasdavi.workshopmongodb.models.User;
import com.lucasdavi.workshopmongodb.repositories.UserRepository;
import com.lucasdavi.workshopmongodb.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
    }

    public User insert(User user) {
        return userRepository.save(user);
    }

    public void delete(String id) {
        findById(id);
        userRepository.deleteById(id);
    }

    public User update(User user) {
        User newUser = findById(user.getId());
        updateData(newUser, user);
        return userRepository.save(newUser);
    }

    private void updateData(User newUser, User user) {
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
    }

    public User fromDTO(UserDTO objDto) {
        return new User(objDto.name(), objDto.email());
    }

}
