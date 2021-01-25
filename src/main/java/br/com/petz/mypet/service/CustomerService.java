package br.com.petz.mypet.service;

import br.com.petz.mypet.entity.Customer;
import br.com.petz.mypet.repository.CustomerRepository;
import br.com.petz.mypet.service.template.CustomerTemplate;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements CustomerTemplate {
    @Autowired
    CustomerRepository customerRepository;

    private static final Logger logger = Logger.getLogger(CustomerService.class);

    @Override
    public boolean saveCustomer(Customer customer){
        try{
            logger.info("#Saving new Customer");
            customerRepository.save(customer);
        }
        catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Optional<Customer> getCustomerById(Integer customerId){
        Optional<Customer> customer = Optional.empty();
        try{
            logger.info(String.format("#Getting customer by id %o", customerId));
            customer = customerRepository.findById(customerId);
        }
        catch (Exception e){
            logger.error(e.getMessage());
        }
        return customer;
    }

    @Override
    public List<Customer> getAllCustomers(){
        logger.info("#Getting all customers");
        return customerRepository.findAll();
    }

    @Override
    public boolean deleteCustomer(Integer customerId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        logger.info(String.format("#Trying to delete customer by customerId %o", customerId));
        if (customerOptional.isPresent()) {
            customerRepository.delete(customerOptional.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean updateCustomerData(Integer customerId, Customer customerData) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        logger.info(String.format("#Trying to update customer by customerId %o", customerId));
        if (customerOptional.isPresent()) {
            Customer updatedCustomer = customerOptional.get();
            BeanUtils.copyProperties(customerData, updatedCustomer, "customerId");
            customerRepository.save(updatedCustomer);
            return true;
        }
        return false;
    }
}
