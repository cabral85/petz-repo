package br.com.petz.mypet.service;

import br.com.petz.mypet.entity.Customer;
import br.com.petz.mypet.entity.Pet;
import br.com.petz.mypet.repository.PetRepository;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class PetServiceTest {

    @InjectMocks
    private IPetService petService;

    @Mock
    private PetRepository petRepository;

    @Rule
    public MockitoRule initRule = MockitoJUnit.rule();

    private Integer DEFAULT_PET_ID = 1;
    private Integer DEFAULT_CUSTOMER_ID = 1;

    private Customer mockDefaultCustomer(String name, String lastName) {
        Customer customer = new Customer();
        customer.setCustomerName(name);
        customer.setCustomerLastName(lastName);
        Calendar birth = Calendar.getInstance();
        birth.set(Calendar.YEAR, 1990);
        birth.set(Calendar.MONTH, Calendar.MARCH);
        birth.set(Calendar.DAY_OF_MONTH, 01);
        customer.setCustomerDateBirth(birth);
        return customer;
    }

    private Pet mockDefaultPet(String petName, String petLastName) {
        Pet pet = new Pet();
        pet.setBreed("labrador");
        pet.setPetName(petName);
        pet.setPetLastName(petLastName);
        Calendar birth = Calendar.getInstance();
        birth.set(Calendar.YEAR, 2010);
        birth.set(Calendar.MONTH, Calendar.MARCH);
        birth.set(Calendar.DAY_OF_MONTH, 01);
        pet.setPetDateBirth(birth);
        pet.setCustomer(mockDefaultCustomer("Someone", "Else"));
        return pet;
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void savePet() {
        Pet pet = mockDefaultPet("Some", "Dog");
        when(petRepository.save(pet)).thenReturn(pet);
        boolean result = petService.savePet(pet);
        assertTrue(result);
    }

    @Test
    void getPetById() {
        Optional<Pet> defaultPet = Optional.of(mockDefaultPet("Someone", "Dog"));
        when(petRepository.findById(DEFAULT_PET_ID)).thenReturn(defaultPet);
        Optional<Pet> result = petService.getPetById(DEFAULT_PET_ID);
        assertEquals(result, defaultPet);
    }

    @Test
    void getAllPets() {
        List<Pet> defaultPet = new ArrayList<Pet>();
        defaultPet.add(mockDefaultPet("Someone", "Dog"));
        when(petRepository.findAll()).thenReturn(defaultPet);
        List<Pet> result = petService.getAllPets();
        assertEquals(result, defaultPet);
    }

    @Test
    void getPetsByCustomerId() {
        List<Pet> defaultPet = new ArrayList<Pet>();
        defaultPet.add(mockDefaultPet("Someone", "Dog"));
        when(petRepository.findByCustomerCustomerId(DEFAULT_CUSTOMER_ID)).thenReturn(defaultPet);
        List<Pet> result = petService.getPetsByCustomerId(DEFAULT_CUSTOMER_ID);
        assertEquals(result, defaultPet);
    }

    @Test
    void getPetsByCustomerName() {
        List<Pet> defaultPet = new ArrayList<Pet>();
        defaultPet.add(mockDefaultPet("Someone", "Dog"));
        when(petRepository.findByCustomerCustomerNameContains("Some")).thenReturn(defaultPet);
        List<Pet> result = petService.getPetsByCustomerName("Some");
        assertEquals(result, defaultPet);
    }

    @Test
    void getPetsByPetName() {
        List<Pet> defaultPet = new ArrayList<Pet>();
        defaultPet.add(mockDefaultPet("Someone", "Dog"));
        when(petRepository.findByPetNameContains("Some")).thenReturn(defaultPet);
        List<Pet> result = petService.getPetsByPetName("Some");
        assertEquals(result, defaultPet);
    }

    @Test
    void deletePet() {
        Pet defaultPet = mockDefaultPet("Somenone", "Dog");
        when(petRepository.findById(DEFAULT_PET_ID)).thenReturn(Optional.of(defaultPet));
        boolean result = petService.deletePet(DEFAULT_PET_ID);
        assertTrue(result);
    }

    @Test
    void updatePetData() {
        Pet defaultPet = mockDefaultPet("Someone", "Dog");
        when(petRepository.findById(DEFAULT_PET_ID)).thenReturn(Optional.of(defaultPet));
        boolean result = petService.updatePetData(DEFAULT_PET_ID, defaultPet);
        assertTrue(result);
    }
}