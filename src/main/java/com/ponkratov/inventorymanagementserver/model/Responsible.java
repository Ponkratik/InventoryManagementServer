package com.ponkratov.inventorymanagementserver.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Responsible {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "responsibleId")
    private long responsibleId;
    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "fio")
    private String fio;
    @ManyToOne
    @JoinColumn(name = "roleId", referencedColumnName = "roleId")
    private Role roleByRoleId;
    @ManyToOne
    @JoinColumn(name = "regionId", referencedColumnName = "regionId")
    private Region regionByRegionId;

    public long getResponsibleId() {
        return responsibleId;
    }

    public void setResponsibleId(long responsibleId) {
        this.responsibleId = responsibleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Responsible that = (Responsible) o;
        return responsibleId == that.responsibleId && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(fio, that.fio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(responsibleId, username, password, fio);
    }

    public Role getRoleByRoleId() {
        return roleByRoleId;
    }

    public void setRoleByRoleId(Role roleByRoleId) {
        this.roleByRoleId = roleByRoleId;
    }

    public Region getRegionByRegionId() {
        return regionByRegionId;
    }

    public void setRegionByRegionId(Region regionByRegionId) {
        this.regionByRegionId = regionByRegionId;
    }
}
