package com.ponkratov.inventorymanagementserver.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ItemAttachmentResponse {
    private String name;
    private String url;
    private String type;
    private long size;
}
