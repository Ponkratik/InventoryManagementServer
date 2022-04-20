package com.ponkratov.inventorymanagementserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:context.xml")
public class InventoryManagementServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryManagementServerApplication.class, args);
    }

}
