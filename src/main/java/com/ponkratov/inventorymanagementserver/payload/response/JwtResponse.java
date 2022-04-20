package com.ponkratov.inventorymanagementserver.payload.response;

import com.ponkratov.inventorymanagementserver.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class JwtResponse {
    private String token;
    private final String type = "Bearer";
    private String username;
    private Role roleByRoleId;
}