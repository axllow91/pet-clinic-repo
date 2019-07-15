package com.mrn.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity  // -> this tells jpa that will be persisted to the db
@Table(name="specialities") // the name of the entity that will be stored in db
public class Speciality extends BaseEntity {

    @Column(name="description")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
