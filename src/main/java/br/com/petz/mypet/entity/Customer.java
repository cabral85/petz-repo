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
@Table(name = "B_CUSTOMER")
public class Customer implements Serializable {
    @Id
    @Column(name = "CUSTOMER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    @Column(name = "NAME", length = 40, nullable = false)
    private String customerName;

    @Column(name = "LAST_NAME", length = 100, nullable = false)
    private String customerLastName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_BIRTH")
    private Calendar customerDateBirth;

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerLastName='" + customerLastName + '\'' +
                ", customerDateBirth=" + customerDateBirth +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerId, customer.customerId) && Objects.equals(customerName, customer.customerName) && Objects.equals(customerLastName, customer.customerLastName) && Objects.equals(customerDateBirth, customer.customerDateBirth);
    }
}
