package com.ponkratov.inventorymanagementserver.controller.impl;

import com.ponkratov.inventorymanagementserver.controller.ItemController;
import com.ponkratov.inventorymanagementserver.model.Item;
import com.ponkratov.inventorymanagementserver.payload.response.MessageResponse;
import com.ponkratov.inventorymanagementserver.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ItemControllerImpl implements ItemController {
    @Autowired
    ItemRepository itemRepository;

    @Override
    public ResponseEntity<?> getAllItems() {
        List<Item> items = itemRepository.findAll();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getItemById(Long itemId) {
        Optional<Item> temp = itemRepository.findById(itemId);
        Item tempItem;
        if (temp.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("Не удалось найти эту мат.ценность"), HttpStatus.NO_CONTENT);
        } else {
            tempItem = temp.get();
        }

        return new ResponseEntity<>(tempItem, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> addItem(Item item) {
        if (itemRepository.existsByInventoryNumber(item.getInventoryNumber())) {
            return new ResponseEntity<>(new MessageResponse("Ошибка: предмет с таким инвентарным номером уже существует"), HttpStatus.BAD_REQUEST);
        }

        if (itemRepository.existsBySerialNumber(item.getSerialNumber())) {
            return new ResponseEntity<>(new MessageResponse("Ошибка: предмет с таким серийным номером уже существует"), HttpStatus.BAD_REQUEST);
        }

        itemRepository.save(item);
        return new ResponseEntity<>(new MessageResponse("Мат.ценность успешно добавлена"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteItem(Long itemId) {
        Optional<Item> temp = itemRepository.findById(itemId);
        Item tempItem;
        if (temp.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("Не удалось найти эту мат.ценность"), HttpStatus.NO_CONTENT);
        } else {
            tempItem = temp.get();
        }

        itemRepository.delete(tempItem);

        return new ResponseEntity<>(new MessageResponse("Мат.ценность удалена успешно"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateItem(Long itemId, Item item) {
        Optional<Item> temp = itemRepository.findById(itemId);
        Item tempItem;
        if (temp.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("Не удалось найти эту мат.ценность"), HttpStatus.NO_CONTENT);
        } else {
            tempItem = temp.get();
        }

        tempItem.setItemName(item.getItemName());
        tempItem.setInventoryNumber(item.getInventoryNumber());
        tempItem.setSerialNumber(item.getSerialNumber());
        tempItem.setDescription(item.getDescription());
        tempItem.setResponsibleByResponsibleId(item.getResponsibleByResponsibleId());
        tempItem.setArrived(item.getArrived());

        itemRepository.save(tempItem);
        return new ResponseEntity<>(new MessageResponse("Данные мат.ценности успешно обновлены"), HttpStatus.OK);
    }
}
