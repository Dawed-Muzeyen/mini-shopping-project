package edu.miu.cs.se.paymentsubsystem.controller;


import edu.miu.cs.se.exceptions.customer.CustomerNotFoundException;
import edu.miu.cs.se.exceptions.order.OrderNotModifiedException;
import edu.miu.cs.se.exceptions.payment.CreditCardNotDeletedException;
import edu.miu.cs.se.exceptions.payment.CreditCardNotFoundException;
import edu.miu.cs.se.exceptions.payment.CreditCardNotSavedException;
import edu.miu.cs.se.paymentsubsystem.model.CreditCard;
import edu.miu.cs.se.paymentsubsystem.service.CreditCardService;
import edu.miu.cs.se.util.ValidateCustomer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/credit-card")
public class CreditCardController {

    private CreditCardService creditCardService;
    private Logger logger = LoggerFactory.getLogger(CreditCardController.class);
    private ValidateCustomer validateCustomer;

    @Autowired
    public CreditCardController(CreditCardService creditCardService, ValidateCustomer validateCustomer) {
        this.creditCardService = creditCardService;
        this.validateCustomer = validateCustomer;
    }
    @PutMapping("/update/{id}")
    public CreditCard updateCreditCardById(@PathVariable("id") long id, @RequestBody CreditCard creditCard) {
        logger.info("/api/v1/credit-card/update/{id} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<CreditCard> creditCardUpdated = creditCardService.updateCreditCardById(id, creditCard);
        return creditCardUpdated.orElseThrow(() -> new OrderNotModifiedException("Credit Card Data is not Updated"));
    }

    @PostMapping("/save/{customer_id}")
    public CreditCard saveCreditCardInfoOfCustomer(@RequestBody CreditCard creditCard, @PathVariable long customer_id) {
        if (!validateCustomer.checkValidCustomer(customer_id))
            throw new CustomerNotFoundException("There is no Customer with ID " + customer_id);


        logger.info("/api/v1/credit-card/save/{customer_id} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<CreditCard> creditCardSaved = creditCardService.saveCreditCardInfoOfCustomer(creditCard, customer_id);
        return creditCardSaved.orElseThrow(() -> new CreditCardNotSavedException("Credit Card Data is not Saved"));
    }

    @PostMapping("/save")
    public CreditCard saveCreditCard(@RequestBody CreditCard creditCard){
        logger.info("/api/v1/credit-card/save is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<CreditCard> creditCardSaved = creditCardService.saveCreditCard(creditCard);
        return creditCardSaved.orElseThrow(() -> new CreditCardNotSavedException("Credit Card Data is not Saved"));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CreditCard>> getAllCreditCard() {
        logger.info("/api/v1/credit-card/all api resource is being fetched");
        Optional<List<CreditCard>> creditCardList = creditCardService.getAllCreditCards();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        return new ResponseEntity<>(creditCardList.orElseThrow(() -> new CreditCardNotFoundException("Credit Card Data is Empty. No information at all")), responseHeaders, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public CreditCard getCreditCardById(@PathVariable("id")long id) {
        logger.info("/api/v1/credit-card/{id} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<CreditCard> creditCardById = creditCardService.getCreditCardById(id);
        return creditCardById.orElseThrow(() -> new CreditCardNotFoundException("Credit Card Data with id " + id + " is not found"));
    }


    @GetMapping("/customer/{customer_id}")
    public List<CreditCard> getCreditCardsByCustomerId(@PathVariable long customer_id){
        logger.info("Checking validity of Customer with customer id " + customer_id);

        if (!validateCustomer.checkValidCustomer(customer_id))
            throw new CustomerNotFoundException("There is no Customer with ID " + customer_id);

        logger.info("/api/v1/credit-card/creditCards/{customer_id} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<List<CreditCard>> creditCardsByCustomerId = creditCardService.getCreditCardsByCustomerId(customer_id);
        return creditCardsByCustomerId.orElseThrow(() -> new CreditCardNotFoundException("Credit Card Data with customer id " + customer_id + " is not found"));

    }

    @GetMapping("/name-on-card/{name_on_card}")
    public List<CreditCard> getByNameOnCard(@PathVariable("name_on_card")String nameOnCard) {
        logger.info("/api/v1/credit-card/name-on-card/{name_on_card} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<List<CreditCard>> creditCardsByNameOnCard = creditCardService.getCreditCardsByNameOnCard(nameOnCard);
        return creditCardsByNameOnCard.orElseThrow(() -> new CreditCardNotFoundException("Credit Card Data with the name  " + nameOnCard + " is not found"));


    }

    @GetMapping("/expiration-date/{expiration_date}")
    public List<CreditCard> getByExpirationDate(@PathVariable("expiration_date")String expirationDate) {
        logger.info("/api/v1/credit-card/expiration-date/{expiration_date} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<List<CreditCard>> creditCardsByExpirationDate = creditCardService.getCreditCardsByExpirationDate(expirationDate);
        return creditCardsByExpirationDate.orElseThrow(() -> new CreditCardNotFoundException("Credit Card Data with the expiration date  " + expirationDate + " is not found"));

    }

    @GetMapping("/card-num/{card_num}")
    public List<CreditCard> getByCardNum(@PathVariable("card_num")String cardNum) {
        logger.info("/api/v1/credit-card/card-num/{card_num} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<List<CreditCard>> creditCardsByCardNum = creditCardService.getCreditCardsByCardNum(cardNum);
        return creditCardsByCardNum.orElseThrow(() -> new CreditCardNotFoundException("Credit Card Data with the card number   " + cardNum + " is not found"));

    }

    @GetMapping("/card-type/{card_type}")
    public  List<CreditCard> getByCardType(@PathVariable("card_type")String cardType) {
        logger.info("/api/v1/credit-card/card-type/{card_type} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<List<CreditCard>> creditCardsByCardType = creditCardService.getCreditCardsByCardType(cardType);
        return creditCardsByCardType.orElseThrow(() -> new CreditCardNotFoundException("Credit Card Data with the card type  " + cardType + " is not found"));

    }

    @DeleteMapping("/delete")
    public CreditCard deleteCreditCard(@RequestBody CreditCard creditCard) {
        logger.info("/api/v1/credit-card/delete is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<CreditCard> creditCardDeleted = creditCardService.deleteCreditCard(creditCard);
        return creditCardDeleted.orElseThrow(() -> new CreditCardNotDeletedException("Credit Card Data is not Deleted"));

    }

    @DeleteMapping("/delete/{id}")
    public CreditCard deleteCreditCardById(@PathVariable("id") long id) {
        logger.info("/api/v1/credit-card/delete/{id} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<CreditCard> creditCard = creditCardService.getCreditCardById(id);
        if (!creditCard.isPresent() || !creditCard.isEmpty())
            throw new CreditCardNotFoundException("There is no Credit Card with id " + id + " . Hence, it is not deleted");


        Optional<CreditCard> creditCardDeleted = creditCardService.deleteCreditCardById(id);
        return creditCardDeleted.orElseThrow(() -> new CreditCardNotDeletedException("Credit Card Data is not Deleted"));
    }


}

