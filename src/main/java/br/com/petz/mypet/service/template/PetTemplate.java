package br.com.petz.mypet.service.template;

import br.com.petz.mypet.entity.Pet;

import java.util.List;
import java.util.Optional;

public interface PetTemplate {
    public boolean savePet(Pet pet);
    public Optional<Pet> getPetById(Integer petId);
    public List<Pet> getAllPets();
    public List<Pet> getPetsByCustomerId(Integer customerId);
    public List<Pet> getPetsByCustomerName(String customerName);
    public List<Pet> getPetsByPetName(String petName);
    public boolean deletePet(Integer petId);
    public boolean updatePetData(Integer petId, Pet petData);
}
