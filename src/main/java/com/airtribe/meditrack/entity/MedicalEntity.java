package com.airtribe.meditrack.entity;

import java.time.LocalDateTime;

public abstract class MedicalEntity implements Cloneable {

    private String id;
    private LocalDateTime createdAt;

    protected MedicalEntity() {
        this.createdAt = LocalDateTime.now();
    }

    protected MedicalEntity(String id) {
        this.id = id;
        this.createdAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    protected void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Return a deep copy of this entity. Subclasses should override to provide concrete copy behavior.
     */
    public abstract MedicalEntity deepCopy();
}
