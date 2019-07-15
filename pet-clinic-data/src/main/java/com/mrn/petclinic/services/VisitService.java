package com.mrn.petclinic.services;

import com.mrn.petclinic.model.Visit;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitService extends CrudService<Visit, Long> {
}
