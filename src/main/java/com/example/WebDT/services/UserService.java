package com.example.WebDT.services;

import jakarta.transaction.Transactional;
import com.example.WebDT.entity.Role;
import com.example.WebDT.entity.User;
import com.example.WebDT.repository.RoleRepository;
import com.example.WebDT.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    public void add(User newUser) {
        userRepository.save(newUser);
    }
    @Transactional
    public void addUserWithRole(User user) {
        User savedUser = userRepository.save(user);

        // Tìm role có ID là 1
        Role role = roleRepository.findById(1).orElseThrow(() -> new RuntimeException("Role not found"));

        savedUser.getRoles().add(role);
        userRepository.save(savedUser);
    }

    public List<User> getUsersByRoleId(Integer roleId) {
        return userRepository.findByRoleId(roleId);
    }
}
