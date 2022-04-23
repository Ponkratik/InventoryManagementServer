package com.ponkratov.inventorymanagementserver.controller.impl;

import com.ponkratov.inventorymanagementserver.controller.RoleController;
import com.ponkratov.inventorymanagementserver.model.Role;
import com.ponkratov.inventorymanagementserver.payload.response.MessageResponse;
import com.ponkratov.inventorymanagementserver.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class RoleControllerImpl implements RoleController {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public ResponseEntity<?> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getRoleById(Long roleId) {
        Optional<Role> temp = roleRepository.findById(roleId);
        Role tempRole;
        if (temp.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("Не удалось найти такую роль"), HttpStatus.NO_CONTENT);
        } else {
            tempRole = temp.get();
        }

        return new ResponseEntity<>(tempRole, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getRoleByRoleName(Long roleId) {
        return null;
    }
}
