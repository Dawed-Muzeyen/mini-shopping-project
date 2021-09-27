package edu.miu.cs.se.customersubsystem.service;

import edu.miu.cs.se.customersubsystem.model.Customer;
import edu.miu.cs.se.customersubsystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class CustomerService {

  private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Optional<List<Customer>> getAllCustomers() {
        return Optional.ofNullable(customerRepository.findAll());
    }

    public Optional<Customer> getCustomerById(long id) {
        return customerRepository.findById(id);
    }
   public  Optional<List<Customer>> getCustomerByFNameLName(String fName, String lName) {
        return customerRepository.selectCustomerByFirstNameAndLastName(fName, lName);
   }
    public  Optional<List<Customer>> getCustomerByFirstName(String bill_firstName) {
        return customerRepository.findByFirstName(bill_firstName);
    }

    public  Optional<List<Customer>> getCustomerByLastName(String bill_lastName) {
        return customerRepository.findByLastName(bill_lastName);
    }

    public  Optional<List<Customer>> getCustomerByEmail(String bill_email) {
        return customerRepository.findByEmail(bill_email);
    }

    public  Optional<List<Customer>> getCustomerByPhoneNumber(String bill_phoneNumber) {
        return customerRepository.findByPhoneNumber(bill_phoneNumber);
    }

    public  Optional<List<Customer>> getCustomerByShipStreet1(String bill_street1) {
        return customerRepository.selectCustomerByShipStreet1(bill_street1);
    }

    public  Optional<List<Customer>> getCustomerByShipStreet2(String bill_street2) {
        return customerRepository.selectCustomerByShipStreet2(bill_street2);
    }

    public  Optional<List<Customer>> getCustomerByShipCity(String bill_city) {
        return customerRepository.selectCustomerByShipCity(bill_city);
    }

    public  Optional<List<Customer>> getCustomerByShipState(String bill_state) {
        return customerRepository.selectCustomerByShipState(bill_state);
    }

    public  Optional<List<Customer>> getCustomerByShipZip(String bill_zipcode) {
        return customerRepository.selectCustomerByShipZip(bill_zipcode);
    }
    public  Optional<List<Customer>> getCustomerByBillStreet1(String ship_street1) {
        return customerRepository.selectCustomerByBillStreet1(ship_street1);
    }

    public  Optional<List<Customer>> getCustomerByBillStreet2(String ship_street2) {
        return customerRepository.selectCustomerByBillStreet2(ship_street2);
    }

    public  Optional<List<Customer>> getCustomerByBillCity(String ship_city) {
        return customerRepository.selectCustomerByBillCity(ship_city);
    }

    public  Optional<List<Customer>> getCustomerByBillState(String ship_state) {
        return customerRepository.selectCustomerByBillState(ship_state);
    }

    public  Optional<List<Customer>> getCustomerByBillZip(String ship_zipcode) {
        return customerRepository.selectCustomerByBillZip(ship_zipcode);
    }

    public List<String> getFirstNameOfAllCustomers() {
       return customerRepository.selectAllCustomerFirstName();
    }

    public List<String> getLastNameOfAllCustomers() {
        return customerRepository.selectAllCustomerLastName();
    }

    public List<String> getEmailOfAllCustomers() {
        return customerRepository.selectAllCustomerEmail();
    }

    public List<String> getPhoneNumberOfAllCustomers() {
        return customerRepository.selectAllCustomerPhoneNumber();
    }

    public List<String> getShipStreet1OfAllCustomers() {
        return customerRepository.selectAllCustomerShipStreet1();
    }

    public List<String> getShipStreet2OfAllCustomers() {
        return customerRepository.selectAllCustomerShipStreet2();
    }

    public List<String> getShipCityOfAllCustomers() {
        return customerRepository.selectAllCustomerShipCity();
    }

    public List<String> getShipStateOfAllCustomers() {
        return customerRepository.selectAllCustomerShipState();
    }

    public List<String> getShipZipOfAllCustomers() {
        return customerRepository.selectAllCustomerShipZip();
    }

    public List<String> getBillStreet1OfAllCustomers() {
        return customerRepository.selectAllCustomerBillStreet1();
    }

    public List<String> getBillStreet2OfAllCustomers() {
        return customerRepository.selectAllCustomerBillStreet2();
    }

    public List<String> getBillCityOfAllCustomers() {
        return customerRepository.selectAllCustomerBillCity();
    }

    public List<String> getBillStateOfAllCustomers() {
        return customerRepository.selectAllCustomerBillState();
    }

    public List<String> getBillZipOfAllCustomers() {
        return customerRepository.selectAllCustomerBillZip();
    }

    public  Optional<Customer> saveCustomer(Customer customer) {
           return Optional.of(customerRepository.save(customer));
    }

    public Optional<Customer> deleteCustomer(Customer customer) {
         customerRepository.delete(customer);
         return Optional.of(customer);
    }

    public Optional<Customer> deleteCustomerById(long id) {
        Optional<Customer> customerDeleted = customerRepository.findById(id);
        customerRepository.deleteById(id);
        return customerDeleted;
    }

    public  Optional<Customer> updateCustomerById(long id, Customer customer) {
        Customer customerTemp = customerRepository.getById(id);

        if(!Optional.of(customer.getFirstName()).isEmpty())
            customerTemp.setFirstName(customer.getFirstName());

        if(!Optional.of(customer.getLastName()).isEmpty())
            customerTemp.setLastName(customer.getLastName());

        if(!Optional.of(customer.getEmail()).isEmpty())
        customerTemp.setEmail(customer.getEmail());

        if(!Optional.of(customer.getPhoneNumber()).isEmpty())
        customerTemp.setPhoneNumber(customer.getPhoneNumber());

        if(!Optional.of(customer.getShipping()).isEmpty())
        customerTemp.setShipping(customer.getShipping());

        if(!Optional.of(customer.getBilling()).isEmpty())
        customerTemp.setBilling(customer.getBilling());

        if(!Optional.of(customer.getCardList()).isEmpty())
        customerTemp.setCardList(customer.getCardList());

        if(!Optional.of(customer.getOrders()).isEmpty())
        customerTemp.setOrders(customer.getOrders());

        return Optional.of(customerRepository.save(customerTemp));
    }

    public List<Customer> saveMoreThanOneCustomer(List<Customer> customers)  throws Exception{
              customers = customerRepository.saveAll(customers);
        return customers;
    }
   /* public List<Customer> saveMoreThanOneCustomer(MultipartFile file)  throws Exception{
        List<Customer> customers = parseCSVFile(file);

        customers = customerRepository.saveAll(customers);
         return customers;
    }
*/
    private List<Customer> parseCSVFile(final MultipartFile file) throws Exception {
        final List<Customer> customers = new ArrayList<>();
        try {
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    final String[] data = line.split(",");
                    final Customer customer = new Customer();
                    customer.setFirstName(data[0]);
                    customer.setLastName(data[1]);
                    customer.setEmail(data[2]);
                    customer.setPhoneNumber(data[3]);
                    String[] shippingAddress =  data[4].split(",");
                    customer.getShipping().setStreet1(shippingAddress[0]);
                    customer.getShipping().setStreet2(shippingAddress[1]);
                    customer.getShipping().setCity(shippingAddress[2]);
                    customer.getShipping().setState(shippingAddress[3]);
                    customer.getShipping().setZipcode(shippingAddress[4]);

                    String[] billingAddress =  data[4].split(",");
                    customer.getBilling().setStreet1(billingAddress[0]);
                    customer.getBilling().setStreet2(billingAddress[1]);
                    customer.getBilling().setCity(billingAddress[2]);
                    customer.getBilling().setState(billingAddress[3]);
                    customer.getBilling().setZipcode(billingAddress[4]);

                    customers.add(customer);
                }
                return customers;
            }
        } catch (final IOException e) {
             throw new Exception("Failed to parse CSV file {}", e);
        }
    }
}
