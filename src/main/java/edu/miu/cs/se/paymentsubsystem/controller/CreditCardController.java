package edu.miu.cs.se.paymentsubsystem.controller;


import edu.miu.cs.se.exceptions.customer.CustomerNotFoundException;
import edu.miu.cs.se.util.ValidatePayment;
import edu.miu.cs.se.paymentsubsystem.model.CreditCard;
import edu.miu.cs.se.paymentsubsystem.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/credit-card")
public class CreditCardController {

    private CreditCardService creditCardService;
    private ValidatePayment validatePayment;

    @Autowired
    public CreditCardController(CreditCardService creditCardService, ValidatePayment validatePayment) {
        this.creditCardService = creditCardService;
        this.validatePayment = validatePayment;
    }
    @PutMapping("/update/{id}")
    public CreditCard updateCreditCardById(@PathVariable("id") long id, @RequestBody CreditCard creditCard) {
        return creditCardService.updateCreditCardById(id, creditCard);
    }

    @PostMapping("/save/{customer_id}")
    public String saveCreditCardInfoOfCustomer(@RequestBody CreditCard creditCard, @PathVariable long customer_id) {
        if(!validatePayment.checkValidPayment(customer_id)) throw new CustomerNotFoundException("There is no Customer with ID " + customer_id);
        creditCardService.saveCreditCardInfoOfCustomer(creditCard, customer_id);
        return "saved successfully";
    }

    @PostMapping("/save")
    public CreditCard saveCreditCard(@RequestBody CreditCard creditCard){
        return creditCardService.saveCreditCard(creditCard);
    }

    @GetMapping("/all")
    public List<CreditCard> getAllCreditCard() {
        return creditCardService.getAllCreditCard();
    }

    @GetMapping("/{id}")
    public CreditCard getCreditCardById(@PathVariable("id")long id) {
        return creditCardService.getCreditCardById(id);
    }


    @GetMapping("/customer/{customer_id}")
    public List<CreditCard> selectCreditCardsByCustomerId(@PathVariable long customer_id){
        return creditCardService.getCreditCardsByCustomerId(customer_id);
    }

    @GetMapping("/name-on-card/{name_on_card}")
    public List<CreditCard> getByNameOnCard(@PathVariable("name_on_card")String nameOnCard) {
        return creditCardService.getByNameOnCard(nameOnCard);
    }

    @GetMapping("/expiration-date/{expiration_date}")
    public List<CreditCard> getByExpirationDate(@PathVariable("expiration_date")String expirationDate) {
        return creditCardService.getByExpirationDate(expirationDate);
    }

    @GetMapping("/card-num/{card_num}")
    public List<CreditCard> getByCardNum(@PathVariable("card_num")String cardNum) {
        return creditCardService.getByCardNum(cardNum);
    }

    @GetMapping("/card-type/{card_type}")
    public  List<CreditCard> getByCardType(@PathVariable("card_type")String cardType) {
        return creditCardService.getByCardType(cardType);
    }

    @DeleteMapping("/delete")
    public void deleteCreditCard(@RequestBody CreditCard creditCard) {
        creditCardService.deleteCreditCard(creditCard);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCreditCardById(@PathVariable("id") long id) {
        creditCardService.deleteCreditCardById(id);
    }


}

