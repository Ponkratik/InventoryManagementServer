package com.ponkratov.inventorymanagementserver.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Item {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "itemId")
    private long itemId;
    @Basic
    @Column(name = "itemName")
    private String itemName;
    @Basic
    @Column(name = "inventoryNumber")
    private String inventoryNumber;
    @Basic
    @Column(name = "serialNumber")
    private String serialNumber;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "arrived")
    private Date arrived;
    @ManyToOne
    @JoinColumn(name = "responsibleId", referencedColumnName = "responsibleId")
    private Responsible responsibleByResponsibleId;

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getArrived() {
        return arrived;
    }

    public void setArrived(Date arrived) {
        this.arrived = arrived;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return itemId == item.itemId && Objects.equals(itemName, item.itemName) && Objects.equals(inventoryNumber, item.inventoryNumber) && Objects.equals(serialNumber, item.serialNumber) && Objects.equals(description, item.description) && Objects.equals(arrived, item.arrived);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, itemName, inventoryNumber, serialNumber, description, arrived);
    }

    public Responsible getResponsibleByResponsibleId() {
        return responsibleByResponsibleId;
    }

    public void setResponsibleByResponsibleId(Responsible responsibleByResponsibleId) {
        this.responsibleByResponsibleId = responsibleByResponsibleId;
    }
}
