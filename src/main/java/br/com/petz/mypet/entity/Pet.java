package br.com.petz.mypet.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@Table(name = "B_PET")
public class Pet implements Serializable {
    @Id
    @Column(name = "PET_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer petId;

    @Column(name = "NAME", nullable = false)
    private String petName;

    @Column(name = "LAST_NAME", length = 100)
    private String petLastName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_BIRTH", nullable = false)
    private Calendar petDateBirth;

    @Column(name = "BREED")
    private String breed;

    @Column(name = "TYPE_PET")
    private String typePet;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Override
    public String toString() {
        return "Pet{" +
                "petId=" + petId +
                ", petName='" + petName + '\'' +
                ", petLastName='" + petLastName + '\'' +
                ", petDateBirth=" + petDateBirth +
                ", breed='" + breed + '\'' +
                ", typePet='" + typePet + '\'' +
                ", customer=" + customer +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(petId, pet.petId) && Objects.equals(petName, pet.petName) && Objects.equals(petLastName, pet.petLastName) && Objects.equals(petDateBirth, pet.petDateBirth) && Objects.equals(breed, pet.breed) && Objects.equals(typePet, pet.typePet) && Objects.equals(customer, pet.customer);
    }
}
