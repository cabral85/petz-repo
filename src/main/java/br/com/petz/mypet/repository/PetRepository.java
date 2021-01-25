package br.com.petz.mypet.repository;

import br.com.petz.mypet.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {
    public List<Pet> findByCustomerCustomerId(Integer customerId);
    public List<Pet> findByPetNameContains(String petName);
    public List<Pet> findByCustomerCustomerNameContains(String customerName);
}
