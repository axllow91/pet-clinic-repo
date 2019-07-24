package com.mrn.petclinic.bootstrap;

import com.mrn.petclinic.model.*;
import com.mrn.petclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component  // --> this becomes spring bean and its get registered into the spring context
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService,
                      PetTypeService petTypeService, SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();

        // we do not want to load every time the data
        if(count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialityService.save(dentistry);

        Owner.builder().address("asdsada").build();


        System.out.println("Loaded pet types...");

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Scofield");
        owner1.setAddress("Some new address 1");
        owner1.setCity("Kansas City");
        owner1.setTelephone("1234");

        // persons pets
        Pet mikePet1 = new Pet();
        mikePet1.setName("Wonder");
        mikePet1.setPetType(savedDogType);
        mikePet1.setOwner(owner1);
        mikePet1.setBirthDate(LocalDate.now());


        owner1.getPets().add(mikePet1);
        ownerService.save(owner1);

        Visit dogVisit = new Visit();
        dogVisit.setPet(mikePet1);
        dogVisit.setDate(LocalDate.now());
        dogVisit.setDescription("MikePet descr");

        visitService.save(dogVisit);

        Owner owner2 = new Owner();
        owner2.setFirstName("Verry");
        owner2.setLastName("Muchida");
        owner2.setAddress("New address");
        owner2.setCity("Zorile City");
        owner2.setTelephone("1234333");

        // persons pets
        Pet verryPet1 = new Pet();
        verryPet1.setName("Miki");
        verryPet1.setPetType(savedCatType);
        verryPet1.setOwner(owner2);
        verryPet1.setBirthDate(LocalDate.now());

        owner2.getPets().add(verryPet1);

        ownerService.save(owner2);

        System.out.println("Loaded owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Alex");
        vet1.setLastName("Dam");
        vet1.getSpeciality().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Menocida");
        vet2.setLastName("Kakarake");
        vet1.getSpeciality().add(savedSurgery);

        vetService.save(vet2);

        System.out.println("Loaded vets....");
    }
}
