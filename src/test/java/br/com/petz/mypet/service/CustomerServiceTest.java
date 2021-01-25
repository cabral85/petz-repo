package br.com.petz.mypet.service;

import br.com.petz.mypet.entity.Customer;
import br.com.petz.mypet.repository.CustomerRepository;
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
class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Rule
    public MockitoRule initRule = MockitoJUnit.rule();

    private Integer DEFAULT_CUSTOMER_ID = 1;

    private Customer mockDefaultCustomer(String name, String lastName){
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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void saveCustomerSuccess() {
        Customer defaultCustomer = mockDefaultCustomer("Somenone", "Else");
        when(customerRepository.save(defaultCustomer)).thenReturn(defaultCustomer);
        boolean saveResult = customerService.saveCustomer(defaultCustomer);
        assertTrue(saveResult);
    }

    @Test
    void getCustomerById() {
        Optional<Customer> defaultCustomer = Optional.of(mockDefaultCustomer("Somenone", "Else"));
        when(customerRepository.findById(DEFAULT_CUSTOMER_ID)).thenReturn(defaultCustomer);
        Optional<Customer> customerResult = customerService.getCustomerById(DEFAULT_CUSTOMER_ID);
        assertEquals(customerResult, defaultCustomer);
    }

    @Test
    void getAllCustomers() {
        List<Customer> defaultCustomer = new ArrayList<Customer>();
        defaultCustomer.add(mockDefaultCustomer("Somenone", "Else"));
        when(customerRepository.findAll()).thenReturn(defaultCustomer);
        List<Customer> result = customerService.getAllCustomers();
        assertEquals(result, defaultCustomer);
    }

    @Test
    void deleteCustomer() {
        Optional<Customer> defaultCustomer = Optional.of(mockDefaultCustomer("Somenone", "Else"));
        when(customerRepository.findById(DEFAULT_CUSTOMER_ID)).thenReturn(defaultCustomer);
        boolean result = customerService.deleteCustomer(DEFAULT_CUSTOMER_ID);
        assertTrue(result);
    }

    @Test
    void updateCustomerData() {
        Optional<Customer> defaultCustomer = Optional.of(mockDefaultCustomer("Somenone", "Else"));
        when(customerRepository.findById(DEFAULT_CUSTOMER_ID)).thenReturn(defaultCustomer);
        boolean result = customerService.updateCustomerData(DEFAULT_CUSTOMER_ID, defaultCustomer.get());
        assertTrue(result);
    }
}