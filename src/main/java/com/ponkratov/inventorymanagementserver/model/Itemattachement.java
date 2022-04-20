package com.ponkratov.inventorymanagementserver.model;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
public class Itemattachement {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "itemAttachementId")
    private long itemAttachementId;
    @Basic
    @Column(name = "fileName")
    private String fileName;
    @Basic
    @Column(name = "fileType")
    private String fileType;
    @Basic
    @Column(name = "data")
    private byte[] data;
    @ManyToOne
    @JoinColumn(name = "itemId", referencedColumnName = "itemId")
    private Item itemByItemId;

    public long getItemAttachementId() {
        return itemAttachementId;
    }

    public void setItemAttachementId(long itemAttachementId) {
        this.itemAttachementId = itemAttachementId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Itemattachement that = (Itemattachement) o;
        return itemAttachementId == that.itemAttachementId && Objects.equals(fileName, that.fileName) && Objects.equals(fileType, that.fileType) && Arrays.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(itemAttachementId, fileName, fileType);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    public Item getItemByItemId() {
        return itemByItemId;
    }

    public void setItemByItemId(Item itemByItemId) {
        this.itemByItemId = itemByItemId;
    }
}
