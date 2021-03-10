package br.com.petz.mypet.service;

import br.com.petz.mypet.entity.Pet;
import br.com.petz.mypet.repository.PetRepository;
import br.com.petz.mypet.service.interfaces.IPet;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService implements IPet {
    @Autowired
    private PetRepository petRepository;

    private static final Logger logger = Logger.getLogger(CustomerService.class);

    @Override
    public boolean savePet(Pet pet) {
        try{
            logger.info("#Saving new Customer");
            petRepository.save(pet);
            return true;
        }
        catch (Exception e){
            logger.error(e.getMessage());
        }
        return false;
    }

    @Override
    public Optional<Pet> getPetById(Integer petId) {
        Optional<Pet> pet = Optional.empty();
        logger.info(String.format("#Getting pet by id %o", petId));
        pet = petRepository.findById(petId);
        return pet;
    }

    @Override
    public List<Pet> getAllPets() {
        List<Pet> pet = null;
        logger.info("#Getting all pets");
        pet = petRepository.findAll();
        return pet;
    }

    @Override
    public List<Pet> getPetsByCustomerId(Integer customerId) {
        List<Pet> petList = null;
        logger.info(String.format("#Getting all customer related to the customerId = %o", customerId));
        petList = petRepository.findByCustomerCustomerId(customerId);
        return petList;
    }

    @Override
    public List<Pet> getPetsByCustomerName(String customerName) {
        List<Pet> petList = null;
        logger.info(String.format("#Getting all pets related to the customerName = %s", customerName));
        petList = petRepository.findByCustomerCustomerNameContains(customerName);
        return petList;
    }

    @Override
    public List<Pet> getPetsByPetName(String petName) {
        List<Pet> petList = null;
        logger.info(String.format("#Getting all pets related to the petName = %s", petName));
        petList = petRepository.findByPetNameContains(petName);
        return petList;
    }

    @Override
    public boolean deletePet(Integer petId) {
        Optional<Pet> petOptional = petRepository.findById(petId);
        logger.info(String.format("#Trying to delete pet by petId %o", petId));
        if (petOptional.isPresent()) {
            petRepository.delete(petOptional.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean updatePetData(Integer petId, Pet petData) {
        Optional<Pet> petOptional = petRepository.findById(petId);
        logger.info(String.format("#Trying to update pet by petId %o", petId));
        if (petOptional.isPresent()) {
            Pet updatedPet = petOptional.get();
            BeanUtils.copyProperties(petData, updatedPet, "petId");
            petRepository.save(updatedPet);
            return true;
        }
        return false;
    }
}
