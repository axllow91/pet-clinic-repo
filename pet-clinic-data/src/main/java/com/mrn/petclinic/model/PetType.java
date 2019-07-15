package com.mrn.petclinic.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder // builder pattern
@Entity
@Table(name="types")
public class PetType extends BaseEntity {
    @Column(name="name")
    private String name;

    @Override
    public String toString() {
        return name;
    }
}
