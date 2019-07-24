package com.mrn.petclinic.services.springdatajpa;

import com.mrn.petclinic.model.Owner;
import com.mrn.petclinic.repositories.OwnerRepository;
import com.mrn.petclinic.repositories.PetRepository;
import com.mrn.petclinic.repositories.PetTypeRepository;
import com.mrn.petclinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Profile("springdatajpa") // -> called this an active profile (the other ownerservice will not be picked up)
public class OwnerSPDJpaService implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;
    private final PetTypeRepository petTypeRepository;

    public OwnerSPDJpaService(OwnerRepository ownerRepository, PetRepository petRepository,
                              PetTypeRepository petTypeRepository) {
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Owner findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {
        return ownerRepository.findAllByLastNameLike(lastName);
    }

    @Override
    public Set<Owner> findAll() {
        Set<Owner> owners = new HashSet<>();
        ownerRepository.findAll().forEach(owners::add); // add each owner found into the owners set and return it
        return owners;
    }

    @Override
    public Owner findById(Long aLong) {
//        Optional<Owner> optionalOwner = ownerRepository.findById(aLong);

//        if (optionalOwner.isPresent())
//            return optionalOwner.get();
//        return null;

        return ownerRepository.findById(aLong).orElse(null);
    }

    @Override
    public Owner save(Owner object) {
        return ownerRepository.save(object);
    }

    @Override
    public void delete(Owner object) {
        ownerRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        ownerRepository.deleteById(aLong);
    }
}
