package br.com.petz.mypet.controller;

import br.com.petz.mypet.entity.Pet;
import br.com.petz.mypet.service.PetService;
import br.com.petz.mypet.service.interfaces.IPet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("pet")
public class PetController {

    @Autowired
    private IPet petService;

    @PostMapping()
    public ResponseEntity<String> savePet(@RequestBody Pet pet) {
        if(petService.savePet(pet)){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updatePet(@RequestBody Pet pet, @PathVariable("id") Integer petId) {
        if(petService.updatePetData(petId, pet)) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePet(@PathVariable("id") Integer petId) {
        if(petService.deletePet(petId)) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping()
    public ResponseEntity<List<Pet>> getPets() {
        List<Pet> petList = petService.getAllPets();
        if(!petList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(petList);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Pet> getPet(@PathVariable("id") Integer petId) {
        Optional<Pet> pet = petService.getPetById(petId);
        if(pet.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(pet.get());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("customer/{id}")
    public ResponseEntity<List<Pet>> getPetByCustomerId(@PathVariable("id") Integer customerId) {
        List<Pet> petList = petService.getPetsByCustomerId(customerId);
        if(!petList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(petList);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("customer/name/{name}")
    public ResponseEntity<List<Pet>> getPetsByCustomerName(@PathVariable("name") String customerName) {
        List<Pet> petList = petService.getPetsByCustomerName(customerName);
        if(!petList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(petList);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Pet>> getPetsByPetName(@PathVariable("name") String petName) {
        List<Pet> petList = petService.getPetsByPetName(petName);
        if(!petList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(petList);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
