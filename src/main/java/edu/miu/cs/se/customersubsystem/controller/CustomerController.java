package edu.miu.cs.se.customersubsystem.controller;


import edu.miu.cs.se.customersubsystem.model.Customer;
import edu.miu.cs.se.customersubsystem.service.CustomerService;
import edu.miu.cs.se.exceptions.customer.CustomerNotDeletedException;
import edu.miu.cs.se.exceptions.customer.CustomerNotFoundException;
import edu.miu.cs.se.exceptions.customer.CustomerNotModifiedException;
import edu.miu.cs.se.exceptions.customer.CustomerNotSavedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private CustomerService customerService;
    Logger logger = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PutMapping("/update/{id}")
    public Customer updateCustomerById(@PathVariable("id") long id, @RequestBody Customer customer) {
        logger.info("/api/v1/customer/update/{id} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<Customer> customerUpdated = customerService.updateCustomerById(id, customer);
        return customerUpdated.orElseThrow(() -> new CustomerNotModifiedException("Customer Data is not Updated"));
    }
    @PostMapping("/save")
    public Customer saveCustomer(@Valid @RequestBody Customer customer) {
        logger.info("/api/v1/customer/save is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<Customer> customerSaved = customerService.saveCustomer(customer);
        return customerSaved.orElseThrow(() -> new CustomerNotSavedException("Customer Data is not Saved"));
    }

    @DeleteMapping("/delete")
    public Customer deleteCustomer(@RequestBody Customer customer) {
        logger.info("/api/v1/customer/delete is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<Customer> customerDeleted = customerService.deleteCustomer(customer);
        return customerDeleted.orElseThrow(() -> new CustomerNotDeletedException("Customer Data is not Deleted"));

    }

    @DeleteMapping("/delete/{id}")
    public Customer deleteCustomerById(@PathVariable("id") long id) {
        logger.info("/api/v1/customer/delete/{id} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<Customer> customer = customerService.getCustomerById(id);
        if(!customer.isPresent() || !customer.isEmpty())
            throw new CustomerNotFoundException("There is no Customer with id " + id + " . Hence, it is not deleted");


        Optional<Customer> customerDeleted = customerService.deleteCustomerById(id);
        return customerDeleted.orElseThrow(() -> new CustomerNotDeletedException("Customer Data is not Deleted"));

    }



    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        logger.info("/api/v1/customer/all api resource is being fetched" );
        Optional<List<Customer>> customerList = customerService.getAllCustomers();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        return new ResponseEntity<>(customerList.orElseThrow(()->new CustomerNotFoundException("Customer Data is Empty. No information at all")), responseHeaders, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable(value = "id") long id) {
        logger.info("/api/v1/customer/{id} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<Customer> customerById = customerService.getCustomerById(id);
        return customerById.orElseThrow(() -> new CustomerNotFoundException("Customer Data with id " + id + " is not found"));
    }

    @GetMapping("/first-name/{first_name}")
    public List<Customer> getCustomerByFirstName(@PathVariable(value = "first_name") String firstName) {
        logger.info("/api/v1/customer/first-name/{first-name} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<List<Customer>> customerByFirstName = customerService.getCustomerByFirstName(firstName);
        return customerByFirstName.orElseThrow(() -> new CustomerNotFoundException("Customer Data with first name " + firstName + " is not found"));

    }

    @GetMapping("/first-last-name")
    public List<Customer> getCustomerByFirstNameAndLastName(@RequestParam(value = "first_name") String firstName, @RequestParam(value = "first_name") String lastName) {

        logger.info("/api/v1/customer/first-last-name is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<List<Customer>> customerByFNameLName = customerService.getCustomerByFNameLName(firstName, lastName);
        return customerByFNameLName.orElseThrow(() -> new CustomerNotFoundException("Customer Data with first name " + firstName + " and last name " + lastName + "  is not found"));

    }

    @GetMapping("/last-name/{last_name}")
    public List<Customer> getCustomerByLastName(@PathVariable(value = "last_name") String lastName) {
        logger.info("/api/v1/customer/last-name/{last_name} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<List<Customer>> customerByLastName = customerService.getCustomerByLastName(lastName);
        return customerByLastName.orElseThrow(() -> new CustomerNotFoundException("Customer Data with last name " + lastName + " is not found"));

    }

    @GetMapping("/email/{email}")
    public List<Customer> getCustomerByEmail(@PathVariable(value = "email") String email) {
        logger.info("/api/v1/customer/email/{email} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<List<Customer>> customerByEmail = customerService.getCustomerByEmail(email);
        return customerByEmail.orElseThrow(() -> new CustomerNotFoundException("Customer Data with email id " + email + " is not found"));

    }

    @GetMapping("/phone-number/{phone_number}")
    public List<Customer> getCustomerByPhoneNumber(@PathVariable(value = "phone_number") String phoneNumber) {
        logger.info("/api/v1/customer/phone-number/{phone_number} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<List<Customer>> customerByPhoneNumber = customerService.getCustomerByPhoneNumber(phoneNumber);
        return customerByPhoneNumber.orElseThrow(() -> new CustomerNotFoundException("Customer Data with Phone Number " + phoneNumber + " is not found"));

    }

    @GetMapping("/address/ship_street1/{ship_street1}")
    public List<Customer> getCustomerByShipStreet1(@PathVariable(value = "ship_street1") String shipStreet1) {
        logger.info("/api/v1/customer/address/ship_street1/{ship_street1} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<List<Customer>> customerByShipStreet1 = customerService.getCustomerByShipStreet1(shipStreet1);
        return customerByShipStreet1.orElseThrow(() -> new CustomerNotFoundException("Customer Data with the shipping Street1 address  " + shipStreet1 + " is not found"));

    }

    @GetMapping("/address/ship_street2/{ship_street2}")
    public List<Customer> getCustomerByShipStreet2(@PathVariable(value = "ship_street2") String shipStreet2) {
        logger.info("/api/v1/customer/address/ship_street2/{ship_street2} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<List<Customer>> customerByShipStreet2 = customerService.getCustomerByShipStreet2(shipStreet2);
        return customerByShipStreet2.orElseThrow(() -> new CustomerNotFoundException("Customer Data with the shipping Street2 address  " + shipStreet2 + " is not found"));
    }

    @GetMapping("/address/ship_city/{ship_city}")
    public List<Customer> getCustomerByShipCity(@PathVariable(value = "ship_city") String shipCity) {
        logger.info("/api/v1/customer/address/ship_city/{ship_city} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<List<Customer>> customerByShipCity = customerService.getCustomerByShipCity(shipCity);
        return customerByShipCity.orElseThrow(() -> new CustomerNotFoundException("Customer Data with the shipping city address  " + shipCity + " is not found"));
       }

    @GetMapping("/address/ship_state/{ship_state}")
    public List<Customer> getCustomerByShipState(@PathVariable(value = "ship_state") String shipState) {
        logger.info("/api/v1/customer/address/ship_state/{ship_state} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<List<Customer>> customerByShipState = customerService.getCustomerByShipState(shipState);
        return customerByShipState.orElseThrow(() -> new CustomerNotFoundException("Customer Data with the shipping state address  " + shipState + " is not found"));

    }

    @GetMapping("/address/ship_zip/{ship_zip}")
    public List<Customer> getCustomerByShipZip(@PathVariable(value = "ship_zip") String shipZip) {
        logger.info("/api/v1/customer/address/ship_zip/{ship_zip} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<List<Customer>> customerByShipZip = customerService.getCustomerByShipZip(shipZip);
        return customerByShipZip.orElseThrow(() -> new CustomerNotFoundException("Customer Data with the shipping zipcode address  " + shipZip + " is not found"));

    }

    @GetMapping("/address/bill_street1/{bill_street1}")
    public List<Customer> getCustomerByBillStreet1(@PathVariable(value = "bill_street1") String billStreet1) {
        logger.info("/api/v1/customer/address/bill_street1/{bill_street1} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<List<Customer>> customerByBillStreet1 = customerService.getCustomerByBillStreet1(billStreet1);
        return customerByBillStreet1.orElseThrow(() -> new CustomerNotFoundException("Customer Data with the billing street 1 address  " + billStreet1 + " is not found"));

    }

    @GetMapping("/address/bill_street2/{bill_street2}")
    public List<Customer> getCustomerByBillStreet2(@PathVariable(value = "bill_street2") String billStreet2) {
        logger.info("/api/v1/customer/address/bill_street2/{bill_street2} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<List<Customer>> customerByBillStreet2 = customerService.getCustomerByBillStreet2(billStreet2);
        return customerByBillStreet2.orElseThrow(() -> new CustomerNotFoundException("Customer Data with the billing street 2 address  " + billStreet2 + " is not found"));

    }

    @GetMapping("/address/bill_city/{bill_city}")
    public List<Customer> getCustomerByBillCity(@PathVariable(value = "bill_city") String billCity) {
        logger.info("/api/v1/customer/address/bill_city/{bill_city} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<List<Customer>> customerByBillCity = customerService.getCustomerByBillCity(billCity);
        return customerByBillCity.orElseThrow(() -> new CustomerNotFoundException("Customer Data with the billing city address  " + billCity + " is not found"));

    }

    @GetMapping("/address/bill_state/{bill_state}")
    public List<Customer> getCustomerByBillState(@PathVariable(value = "bill_state") String billState) {
        logger.info("/api/v1/customer/address/bill_state/{bill_state} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<List<Customer>> customerByBillState = customerService.getCustomerByBillState(billState);
        return customerByBillState.orElseThrow(() -> new CustomerNotFoundException("Customer Data with the billing state address  " + billState + " is not found"));
          }
    @GetMapping("/address/bill_zip/{bill_zip}")
    public List<Customer> getCustomerByBillZip(@PathVariable(value = "bill_zip") String billZip) {
        logger.info("/api/v1/customer/address/bill_zip/{bill_zip} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<List<Customer>> customerByBillZip = customerService.getCustomerByBillZip(billZip);
        return customerByBillZip.orElseThrow(() -> new CustomerNotFoundException("Customer Data with the billing zipcode address  " + billZip + " is not found"));

    }
    @GetMapping("/all/first_name")
    public List<String> getAllFirstNameOfCustomers() {
        return customerService.getFirstNameOfAllCustomers();
    }

    @GetMapping("/all/last_name")
    public List<String> getAllLastNameOfCustomers() {
        return customerService.getLastNameOfAllCustomers();
    }

    @GetMapping("/all/email")
    public List<String> getAllEmailOfCustomers() {
        return customerService.getEmailOfAllCustomers();
    }

    @GetMapping("/all/phone_number")
    public List<String> getAllPhoneNumberOfCustomers() {
        return customerService.getPhoneNumberOfAllCustomers();
    }

    @GetMapping("/all/ship_street1")
    public List<String> getAllShipStreet1OfCustomers() {
        return customerService.getShipStreet1OfAllCustomers();
    }

    @GetMapping("/all/ship_street2")
    public List<String> getAllShipStreet2OfCustomers() {
        return customerService.getShipStreet2OfAllCustomers();
    }

    @GetMapping("/all/ship_city")
    public List<String> getAllShipCityOfCustomers() {
        return customerService.getShipCityOfAllCustomers();
    }

    @GetMapping("/all/ship_state")
    public List<String> getAllShipStateOfCustomers() {
        return customerService.getShipStateOfAllCustomers();
    }

    @GetMapping("/all/ship_zip")
    public List<String> getAllShipZipOfCustomers() {
        return customerService.getShipZipOfAllCustomers();
    }

    @GetMapping("/all/bill_street1")
    public List<String> getAllBillStreet1OfCustomers() {
        return customerService.getBillStreet1OfAllCustomers();
    }

    @GetMapping("/all/bill_street2")
    public List<String> getAllBillStreet2OfCustomers() {
        return customerService.getBillStreet2OfAllCustomers();
    }

    @GetMapping("/all/bill_city")
    public List<String> getAllBillCityOfCustomers() {
        return customerService.getBillCityOfAllCustomers();
    }

    @GetMapping("/all/bill_state")
    public List<String> getAllBillStateOfCustomers() {
        return customerService.getBillStateOfAllCustomers();
    }

    @GetMapping("/all/bill_zip")
    public List<String> getAllBillZipOfCustomers() {
        return customerService.getBillZipOfAllCustomers();
    }



}
