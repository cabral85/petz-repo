package br.com.petz.mypet.service.template;

import br.com.petz.mypet.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerTemplate {
    public boolean saveCustomer(Customer customer);
    public Optional<Customer> getCustomerById(Integer customerId);
    public List<Customer> getAllCustomers();
    public boolean deleteCustomer(Integer customerId);
    public boolean updateCustomerData(Integer customerId, Customer customerData);
}
