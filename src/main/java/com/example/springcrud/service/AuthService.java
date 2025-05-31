package com.example.springcrud.service;

import com.example.springcrud.dto.RegisterRequest;
import com.example.springcrud.model.Profile;
import com.example.springcrud.model.Role;
import com.example.springcrud.model.User;
import com.example.springcrud.repository.ProfileRepository;
import com.example.springcrud.repository.RoleRepository;
import com.example.springcrud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       ProfileRepository profileRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.profileRepository = profileRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(RegisterRequest request) {
        // Проверка — пользователь уже существует?
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return "Пользователь с таким именем уже существует";
        }

        // Создание пользователя
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // Получение или создание роли ROLE_USER
        Optional<Role> userRoleOpt = roleRepository.findByName("ROLE_USER");
        Role userRole = userRoleOpt.orElseGet(() -> {
            Role role = new Role("ROLE_USER");
            roleRepository.save(role);
            return role;
        });

        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        user.setRoles(roles);

        // Создание профиля и привязка к пользователю
        Profile profile = new Profile();
        profile.setFirstName(request.getFirstName());
        profile.setLastName(request.getLastName());
        profile.setEmail(request.getEmail());

        user.setProfile(profile);

        // Сохраняем пользователя (профиль тоже сохранится, если каскад включен)
        userRepository.save(user);

        return "Пользователь успешно зарегистрирован";
    }
}