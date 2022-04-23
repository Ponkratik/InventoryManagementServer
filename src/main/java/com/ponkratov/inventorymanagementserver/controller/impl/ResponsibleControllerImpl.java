package com.ponkratov.inventorymanagementserver.controller.impl;

import com.ponkratov.inventorymanagementserver.controller.ResponsibleController;
import com.ponkratov.inventorymanagementserver.model.Responsible;
import com.ponkratov.inventorymanagementserver.payload.request.SignupRequest;
import com.ponkratov.inventorymanagementserver.payload.response.MessageResponse;
import com.ponkratov.inventorymanagementserver.repository.RegionRepository;
import com.ponkratov.inventorymanagementserver.repository.ResponsibleRepository;
import com.ponkratov.inventorymanagementserver.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ResponsibleControllerImpl implements ResponsibleController {
    @Autowired
    ResponsibleRepository responsibleRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    RegionRepository regionRepository;

    @Override
    public ResponseEntity<?> getAllResponsibles() {
        List<Responsible> responsibles = responsibleRepository.findAll();
        return new ResponseEntity<>(responsibles, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getResponsibleById(Long responsibleId) {
        Optional<Responsible> temp = responsibleRepository.findByResponsibleId(responsibleId);
        Responsible tempResponsible;
        if (temp.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("Не удалось найти этого мат.ответственного"), HttpStatus.NO_CONTENT);
        } else {
            tempResponsible = temp.get();
        }

        return new ResponseEntity<>(tempResponsible, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteResponsible(Long responsibleId) {
        Optional<Responsible> temp = responsibleRepository.findByResponsibleId(responsibleId);
        Responsible tempResponsible;
        if (temp.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("Не удалось найти этого мат.ответственного"), HttpStatus.NO_CONTENT);
        } else {
            tempResponsible = temp.get();
        }

        responsibleRepository.delete(tempResponsible);

        return new ResponseEntity<>(new MessageResponse("Мат.ответственный удалён успешно"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateResponsible(Long responsibleId, SignupRequest request) {
        Optional<Responsible> temp = responsibleRepository.findByResponsibleId(responsibleId);
        Responsible tempResponsible;
        if (temp.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("Не удалось найти этого мат.ответственного"), HttpStatus.NO_CONTENT);
        } else {
            tempResponsible = temp.get();
        }

        tempResponsible.setUsername(request.getUsername());
        tempResponsible.setFio(request.getFio());
        tempResponsible.setRoleByRoleId(request.getRoleByRoleId());
        tempResponsible.setRegionByRegionId(request.getRegionByRegionId());

        responsibleRepository.save(tempResponsible);
        return new ResponseEntity<>(new MessageResponse("Данные мат.ответственного успешно обновлены"), HttpStatus.OK);
    }
}
