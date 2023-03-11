package com.example.contractcore.Domain;

import lombok.Getter;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
public abstract class MainBaseEntity extends BaseEntity {

    private Boolean isDeleted;


    @Override
    public void beforeCreate() {
        super.beforeCreate();
        this.isDeleted = this.isDeleted != null && this.isDeleted;
    }
}
