package br.com.petz.mypet.entity;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.util.Calendar;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class PetTest {

    @Rule
    public MockitoRule initRule = MockitoJUnit.rule();

    @InjectMocks
    private Pet pet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        pet = new Pet();
    }

    private Calendar getPetBirth(){
        Calendar birth = Calendar.getInstance();
        birth.set(Calendar.YEAR, 1990);
        birth.set(Calendar.MONTH, Calendar.MARCH);
        birth.set(Calendar.DAY_OF_MONTH, 01);
        birth.set(Calendar.HOUR, 0);
        birth.set(Calendar.MINUTE, 0);
        birth.set(Calendar.MILLISECOND, 0);
        return birth;
    }

    private Pet getPetData(String name, String lastName){
        pet.setPetName(name);
        pet.setPetLastName(lastName);
        pet.setCustomer(new Customer());
        pet.setPetDateBirth(getPetBirth());
        pet.setBreed("Some Breed");
        pet.setTypePet("Dog");
        return pet;
    }

    @Test
    void testGettersAndSetters() {
        Pet pet = getPetData("Some", "Dog");
        assertEquals(pet.getPetName(), "Some");
        assertEquals(pet.getPetLastName(), "Dog");
        assertEquals(pet.getTypePet(), "Dog");
        assertEquals(pet.getBreed(), "Some Breed");
        assertEquals(pet.getCustomer(), new Customer());
        assertEquals(pet.getPetDateBirth(), getPetBirth());
    }

    @Test
    void testEquals() {
        Pet pet1 = getPetData("Some", "Dog");
        Pet pet2 = getPetData("Some", "Dog");
        assertTrue(pet1.equals(pet2));
    }

    @Test
    void testToString() {
        Pet pet = getPetData("Some", "Dog");
        String result = "Pet{" +
                "petId=" + pet.getPetId() +
                ", petName='" + pet.getPetName() + '\'' +
                ", petLastName='" + pet.getPetLastName() + '\'' +
                ", petDateBirth=" + pet.getPetDateBirth() +
                ", breed='" + pet.getBreed() + '\'' +
                ", typePet='" + pet.getTypePet() + '\'' +
                ", customer=" + pet.getCustomer() +
                '}';

        assertEquals(pet.toString(), result);
    }
}