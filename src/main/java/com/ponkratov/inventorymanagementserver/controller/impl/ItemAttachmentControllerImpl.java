package com.ponkratov.inventorymanagementserver.controller.impl;

import com.ponkratov.inventorymanagementserver.controller.ItemAttachmentController;
import com.ponkratov.inventorymanagementserver.model.Item;
import com.ponkratov.inventorymanagementserver.model.Itemattachement;
import com.ponkratov.inventorymanagementserver.payload.response.ItemAttachmentResponse;
import com.ponkratov.inventorymanagementserver.payload.response.MessageResponse;
import com.ponkratov.inventorymanagementserver.repository.ItemAttachmentRepository;
import com.ponkratov.inventorymanagementserver.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Stream;

@RestController
public class ItemAttachmentControllerImpl implements ItemAttachmentController {
    @Autowired
    ItemAttachmentRepository itemAttachmentRepository;

    @Autowired
    ItemRepository itemRepository;

    @Override
    public ResponseEntity<?> getItemAttachmentsByItemId(Long itemId) {
        Stream<Itemattachement> fileRecords = itemAttachmentRepository.findByItemByItemId_ItemId(itemId).stream();
        List<ItemAttachmentResponse> files = fileRecords.map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/itemattachment/get/")
                    .path(String.valueOf(dbFile.getItemAttachementId()))
                    .toUriString();
            return new ItemAttachmentResponse(
                    dbFile.getFileName(),
                    fileDownloadUri,
                    dbFile.getFileType(),
                    dbFile.getData().length);
        }).toList();

        return new ResponseEntity<>(files, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getItemAttachmentByItemAttachmentId(Long itemAttachmentId) {
        Itemattachement file = itemAttachmentRepository.getById(itemAttachmentId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                .body(file.getData());
    }

    @Override
    public ResponseEntity<?> addItemAttachment(Long itemId, MultipartFile file) {
        Item item = itemRepository.getById(itemId);
        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Itemattachement fileDB = new Itemattachement(0, fileName, file.getContentType(), file.getBytes(), item);
            itemAttachmentRepository.save(fileDB);

            return new ResponseEntity<>(new MessageResponse("Файл загружен успешно"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new MessageResponse("Не удалось загрузить файл"), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @Override
    public ResponseEntity<?> deleteItemAttachment(Long itemAttachmentId) {
        itemAttachmentRepository.deleteById(itemAttachmentId);
        return new ResponseEntity<>(new MessageResponse("Файл удалён успешно"), HttpStatus.OK);
    }
}
