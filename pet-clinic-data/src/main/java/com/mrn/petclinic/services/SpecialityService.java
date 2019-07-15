package com.mrn.petclinic.services;

import com.mrn.petclinic.model.Speciality;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialityService extends CrudService<Speciality, Long> {
}
