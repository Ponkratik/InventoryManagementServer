package com.ponkratov.inventorymanagementserver.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Role {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "roleId")
    private long roleId;
    @Basic
    @Column(name = "roleName")
    private String roleName;
    @Basic
    @Column(name = "roleNameLocal")
    private String roleNameLocal;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return roleId == role.roleId && Objects.equals(roleName, role.roleName) && Objects.equals(roleNameLocal, role.roleNameLocal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, roleName, roleNameLocal);
    }
}
