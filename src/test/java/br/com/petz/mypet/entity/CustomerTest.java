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

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class CustomerTest {

    @InjectMocks
    private Customer customer;

    @Rule
    public MockitoRule initRule = MockitoJUnit.rule();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        customer = new Customer();
    }

    private Calendar getCustomerBirth() {
        Calendar birth = Calendar.getInstance();
        birth.set(Calendar.YEAR, 1990);
        birth.set(Calendar.MONTH, Calendar.MARCH);
        birth.set(Calendar.DAY_OF_MONTH, 01);
        birth.set(Calendar.HOUR, 0);
        birth.set(Calendar.MINUTE, 0);
        birth.set(Calendar.MILLISECOND, 0);
        return birth;
    }

    private Customer setCustomer() {
        customer.setCustomerId(1);
        customer.setCustomerName("Someone");
        customer.setCustomerLastName("Else");
        customer.setCustomerDateBirth(getCustomerBirth());
        return customer;
    }

    @Test
    void testSetterAndGetter() {
        Customer customer = setCustomer();
        assertEquals(customer.getCustomerId(), 1);
        assertEquals(customer.getCustomerName(), "Someone");
        assertEquals(customer.getCustomerLastName(), "Else");
        assertEquals(customer.getCustomerDateBirth(), getCustomerBirth());
    }

    @Test
    void testEquals() {
        Customer setCustomer = setCustomer();
        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setCustomerName("Someone");
        customer.setCustomerLastName("Else");
        customer.setCustomerDateBirth(getCustomerBirth());
        assertTrue(customer.equals(setCustomer));
    }

    @Test
    void testToString() {
        Customer setCustomer = setCustomer();
        String result = "Customer{" +
                "customerId=" + setCustomer.getCustomerId() +
                ", customerName='" + setCustomer.getCustomerName() + '\'' +
                ", customerLastName='" + setCustomer.getCustomerLastName() + '\'' +
                ", customerDateBirth=" + setCustomer.getCustomerDateBirth() +
                '}';
        assertEquals(setCustomer.toString(), result);
    }
}