package com.mrn.petclinic.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor // to make sure builder pattern works
@Builder // builder pattern
@Entity
@Table(name="vets")
public class Vet extends Person {
    @ManyToMany(fetch = FetchType.EAGER) // by default everything that starts with many is lazy initialized
    // eager -> jpa will try to load everything all at ones.
    // lazy -> will not load until we ask for it
    /*
    * A join table is typically used in the mapping of many-to-many and unidirectional one-to-many associations.
    * It may also be used to map bidirectional many-to-one/one-to-many associations,
    * unidirectional many-to-one relationships, and one-to-one associations (both bidirectional and unidirectional).
    * */
    @JoinTable(name="vet_specialities", joinColumns = @JoinColumn(name="vet_id"),
                    inverseJoinColumns = @JoinColumn(name="speciality_id"))
    private Set<Speciality> speciality = new HashSet<>(); // to escape the null excp

}
