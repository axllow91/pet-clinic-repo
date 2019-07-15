package com.mrn.petclinic.services;

import com.mrn.petclinic.model.Pet;
import org.springframework.stereotype.Repository;

@Repository
public interface PetService extends CrudService<Pet, Long> {

}
