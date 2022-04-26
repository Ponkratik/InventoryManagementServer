package com.ponkratov.inventorymanagementserver.controller;

import com.ponkratov.inventorymanagementserver.model.Item;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/item")
@CrossOrigin(origins = "*", maxAge = 3600)
public interface ItemController {
    @GetMapping("/get/all")
    @PreAuthorize("hasRole('ROLE_SYSADMIN')")
    ResponseEntity<?> getAllItems();

    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('ROLE_SYSADMIN')")
    ResponseEntity<?> getItemById(@PathVariable("id") Long itemId);

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_SYSADMIN')")
    ResponseEntity<?> addItem(@Valid @RequestBody Item item);

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_SYSADMIN')")
    ResponseEntity<?> deleteItem(@PathVariable("id") Long itemId);

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ROLE_SYSADMIN')")
    ResponseEntity<?> updateItem(@PathVariable("id") Long itemId, @Valid @RequestBody Item item);
}
