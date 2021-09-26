package edu.miu.cs.se.paymentsubsystem.service;

import edu.miu.cs.se.customersubsystem.service.CustomerService;
import edu.miu.cs.se.paymentsubsystem.model.CreditCard;
import edu.miu.cs.se.paymentsubsystem.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

@Service
@Transactional
public class CreditCardService {
    private CreditCardRepository creditCardRepository;
    private CustomerService customerService;

    @Autowired
    public CreditCardService(CreditCardRepository creditCardRepository, CustomerService customerService) {
        this.creditCardRepository = creditCardRepository;
        this.customerService = customerService;
    }

    public List<CreditCard> getAllCreditCard() {
        return creditCardRepository.findAll();
    }

    public CreditCard saveCreditCard(CreditCard creditCard) {
        return creditCardRepository.save(creditCard);
    }

    public CreditCard getCreditCardById(long id) {
        return creditCardRepository.findById(id).get();
    }

    public void deleteCreditCard(CreditCard creditCard) {
        creditCardRepository.delete(creditCard);
    }

    public void deleteCreditCardById(long id) {
        creditCardRepository.deleteById(id);
    }

    public CreditCard updateCreditCardById(long id, CreditCard creditCard) {
        CreditCard creditCardTemp = creditCardRepository.getById(id);

        if(!Optional.of(creditCard.getNameOnCard()).isEmpty())
            creditCardTemp.setNameOnCard(creditCard.getNameOnCard());

        if(!Optional.of(creditCard.getExpirationDate()).isEmpty())
            creditCardTemp.setExpirationDate(creditCard.getExpirationDate());

        if(!Optional.of(creditCard.getCardNum()).isEmpty())
            creditCardTemp.setCardNum(creditCard.getCardNum());

        if(!Optional.of(creditCard.getCardType()).isEmpty())
            creditCardTemp.setCardType(creditCard.getCardType());

        return creditCardRepository.save(creditCardTemp);
    }

    public void saveCreditCardInfoOfCustomer(CreditCard creditCard, long customer_id) {


        Set<Long> allIds = creditCardRepository.allCreditCardIds();
        long id = new Random().nextLong();
        while(allIds.contains(id)){
            id = new Random().nextLong();
        }
        creditCardRepository.insertCreditCardInfoOfCustomer(id, creditCard.getNameOnCard(), creditCard.getExpirationDate(), creditCard.getCardNum(),
                creditCard.getCardType(),customer_id);
    }

    public List<CreditCard> getCreditCardsByCustomerId(long customer_id){
        return creditCardRepository.selectCreditCardsByCustomerId(customer_id);
    }

    public List<CreditCard> getByNameOnCard(String nameOnCard) {
        return creditCardRepository.findByNameOnCard(nameOnCard);
    }
    public List<CreditCard> getByExpirationDate(String expirationDate) {
        return creditCardRepository.findByExpirationDate(expirationDate);
    }
    public List<CreditCard> getByCardNum(String cardNum) {
        return creditCardRepository.findByCardNum(cardNum);
    }
    public  List<CreditCard> getByCardType(String cardType) {
        return creditCardRepository.findByCardType(cardType);
    }



}

