package com.mrn.petclinic.controllers;

import com.mrn.petclinic.model.Owner;
import com.mrn.petclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController ownerController;

    Set<Owner> ownerSet;

    MockMvc mockMvc; // sets the controller to be test (ownercontroller)

    @BeforeEach
    void setUp() {
      ownerSet = new HashSet<>();
      ownerSet.add(Owner.builder().id(1L).build());
      ownerSet.add(Owner.builder().id(2L).build());

      // for each test method it initializes a mock environment
      mockMvc = MockMvcBuilders
              .standaloneSetup(ownerController)
              .build();
    }

    @Test
    void listOwners() throws Exception {
        when(ownerService.findAll()).thenReturn(ownerSet);
        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                // returns the "owners/index" view
                .andExpect(view().name("owner/index"))
                .andExpect(model().attribute("owners", hasSize(5)));

    }

    @Test
    void listOwnersByIndex() throws Exception {
        when(ownerService.findAll()).thenReturn(ownerSet);
        mockMvc.perform(get("/owners/index"))
                .andExpect(status().isOk())
                // returns the "owners/index" view
                .andExpect(view().name("owner/index"))
                .andExpect(model().attribute("owners", hasSize(2)));

    }

    @Test
    void findOwners() throws Exception{
        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("notImplemented"));

        // Verifies that no interactions happened on given mocks
        // beyond the previously verified interactions
        verifyZeroInteractions(ownerService);

    }
}