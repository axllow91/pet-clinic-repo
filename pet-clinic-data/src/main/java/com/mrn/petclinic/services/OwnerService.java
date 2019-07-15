package com.mrn.petclinic.services;

import com.mrn.petclinic.model.Owner;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerService extends CrudService<Owner, Long>{
    Owner findByLastName(String lastName);

}
