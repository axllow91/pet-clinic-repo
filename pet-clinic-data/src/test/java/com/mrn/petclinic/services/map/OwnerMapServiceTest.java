package com.mrn.petclinic.services.map;

import com.mrn.petclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {


    OwnerMapService ownerMapService;

    final Long ownerId = 1L;
    final String lastName = "Smith";

    final Long ownerId2 = 2L;

    // before each - must not be private and static
    // before each will execute this method every time before every method in this class will run
    @BeforeEach
    void setUp() {
         ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
         ownerMapService.save(Owner.builder().id(ownerId).lastName(lastName).build());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerMapService.findAll();

        // expect only one owner (size is 1)
        assertEquals(1, ownerSet.size());
    }

    @Test
    void saveExistingId() {
        // id to test
        Long id = 2L;

        Owner owner2 = Owner.builder().id(ownerId2).build();
        Owner savedOwner = ownerMapService.save(owner2);

        assertEquals(id, savedOwner.getId());
    }

    @Test
    void saveNoId() {
        Owner savedOwner = ownerMapService.save(Owner.builder().build());

        // test if object exists
        assertNotNull(savedOwner);
        // test if id of that object exists
        assertNotNull(savedOwner.getId());
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerId));

        // every time the setUp function will create a new owner
        // and after we delete it the owner obj
        // we test to see if the size of the Set is 0 (because we had only one obj)
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId);

        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void findById() {
        Long id = 1L;
        Owner savedOwner = ownerMapService.findById(ownerId);
        // System.out.println(savedOwner.getId());

        assertNotNull(savedOwner);
        assertEquals(id, savedOwner.getId());

    }

    @Test
    void findByLastName() {
        Owner smith = ownerMapService.findByLastName(lastName);

        assertNotNull(smith);
        assertEquals(ownerId, smith.getId());
    }
}