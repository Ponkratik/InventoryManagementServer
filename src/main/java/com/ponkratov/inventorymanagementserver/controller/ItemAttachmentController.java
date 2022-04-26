package com.ponkratov.inventorymanagementserver.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/itemattachment")
@CrossOrigin(origins = "*", maxAge = 3600)
public interface ItemAttachmentController {
    @GetMapping("/get/item/{id}")
    @PreAuthorize("hasRole('ROLE_SYSADMIN')")
    ResponseEntity<?> getItemAttachmentsByItemId(@PathVariable("id") Long itemId);

    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('ROLE_SYSADMIN')")
    ResponseEntity<?> getItemAttachmentByItemAttachmentId(@PathVariable("id") Long itemAttachmentId);

    @PostMapping("/add/item/{id}")
    @PreAuthorize("hasRole('ROLE_SYSADMIN')")
    ResponseEntity<?> addItemAttachment(@PathVariable("id") Long itemId, @RequestParam("file") MultipartFile file);

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_SYSADMIN')")
    ResponseEntity<?> deleteItemAttachment(@PathVariable("id") Long itemAttachmentId);
}
