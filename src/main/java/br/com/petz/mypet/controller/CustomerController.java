package br.com.petz.mypet.controller;

import br.com.petz.mypet.entity.Customer;
import br.com.petz.mypet.service.interfaces.ICustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private ICustomer customerService;


    @PostMapping()
    public ResponseEntity<String> saveCustomer(@RequestBody Customer customer) {
        if(customerService.saveCustomer(customer)){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateCustomer(@RequestBody Customer customer, @PathVariable("id") Integer customerId) {
        if(customerService.updateCustomerData(customerId, customer)) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") Integer customerId) {
        if(customerService.deleteCustomer(customerId)) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping()
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customerList = customerService.getAllCustomers();
        if(!customerList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(customerList);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Integer customerId) {
        Optional<Customer> customer = customerService.getCustomerById(customerId);
        if(customer.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(customer.get());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
