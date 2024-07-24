package com.example.integrador.service;

import com.example.integrador.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserServiceImpl implements UserService {

    private List<User> users = new ArrayList<>();
    private AtomicLong idCounter = new AtomicLong();

    // Constructor para añadir usuarios iniciales
    public UserServiceImpl() {
        // Inicializar datos
        users.add(new User(1L, "Sebastian Hernández", "hernandezsebastian1796@gmail.com"));
        users.add(new User(2L, "Daniel Hernández", "danielhernandez@emilianisomascos.com"));
    }

    @Override
    public User createUser(User user) {
        user.setId(idCounter.incrementAndGet());
        users.add(user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    @Override
    public User updateUser(Long id, User user) {
        User existingUser = getUserById(id).orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        return existingUser;
    }

    @Override
    public void deleteUser(Long id) {
        users.removeIf(user -> user.getId().equals(id));
    }
}
