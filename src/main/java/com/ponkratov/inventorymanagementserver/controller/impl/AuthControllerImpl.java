package com.ponkratov.inventorymanagementserver.controller.impl;

import com.ponkratov.inventorymanagementserver.controller.AuthController;
import com.ponkratov.inventorymanagementserver.model.Responsible;
import com.ponkratov.inventorymanagementserver.model.Role;
import com.ponkratov.inventorymanagementserver.payload.request.SigninRequest;
import com.ponkratov.inventorymanagementserver.payload.request.SignupRequest;
import com.ponkratov.inventorymanagementserver.payload.response.JwtResponse;
import com.ponkratov.inventorymanagementserver.payload.response.MessageResponse;
import com.ponkratov.inventorymanagementserver.repository.ResponsibleRepository;
import com.ponkratov.inventorymanagementserver.repository.RoleRepository;
import com.ponkratov.inventorymanagementserver.security.jwt.JwtUtils;
import com.ponkratov.inventorymanagementserver.security.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AuthControllerImpl implements AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    ResponsibleRepository responsibleRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtUtils jwtUtils;

    @Override
    public ResponseEntity<?> authenticateUser(SigninRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> rolesStr = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();

        Role role = roleRepository.findByRoleName(rolesStr.get(0)).get();
        /*List<Role> roles = new ArrayList<>();
        for (String s : rolesStr) {
            Role r = new Role();
            r = roleRepository.findByRoleName(s).get();
            roles.add(r);
        }*/

        return new ResponseEntity<>(new JwtResponse(jwt,
                userDetails.getUsername(),
                role), HttpStatus.OK);


    }

    @Override
    public ResponseEntity<?> registerUser(SignupRequest signupRequest) {
        if (responsibleRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Ошибка: пользователь с таким логином уже существует"));
        }

        Responsible responsible = new Responsible();
        responsible.setUsername(signupRequest.getUsername());
        responsible.setPassword(signupRequest.getPassword());
        responsible.setFio(signupRequest.getFio());
        responsible.setRoleByRoleId(signupRequest.getRoleByRoleId());
        responsible.setRegionByRegionId(signupRequest.getRegionByRegionId());

        responsibleRepository.save(responsible);
        return new ResponseEntity<>(new MessageResponse("Пользователь успешно зарегистрирован"), HttpStatus.OK);
    }
}
