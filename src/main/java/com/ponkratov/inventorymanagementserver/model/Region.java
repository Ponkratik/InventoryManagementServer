package com.ponkratov.inventorymanagementserver.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Region {
    @Id
    @Column(name = "regionId")
    private long regionId;
    @Basic
    @Column(name = "regionName")
    private String regionName;

    public long getRegionId() {
        return regionId;
    }

    public void setRegionId(long regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Region region = (Region) o;
        return regionId == region.regionId && Objects.equals(regionName, region.regionName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regionId, regionName);
    }
}
