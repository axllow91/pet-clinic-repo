package com.mrn.petclinic.services;

import com.mrn.petclinic.model.Vet;
import org.springframework.stereotype.Repository;

@Repository
public interface VetService extends CrudService<Vet, Long> {

}
