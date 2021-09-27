package edu.miu.cs.se.paymentsubsystem.service;

import edu.miu.cs.se.customersubsystem.service.CustomerService;
import edu.miu.cs.se.paymentsubsystem.model.CreditCard;
import edu.miu.cs.se.paymentsubsystem.repository.CreditCardRepository;
import edu.miu.cs.se.shoppingsubsystem.model.ShoppingCart;
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

    public Optional<List<CreditCard>> getAllCreditCards() {
        return Optional.of(creditCardRepository.findAll());
    }

    public Optional<CreditCard>  saveCreditCard(CreditCard creditCard) {
        return Optional.of(creditCardRepository.save(creditCard));
    }

    public Optional<CreditCard>  getCreditCardById(long id) {
        return creditCardRepository.findById(id);
    }

    public Optional<CreditCard>  deleteCreditCard(CreditCard creditCard) {
        creditCardRepository.delete(creditCard);
       return Optional.of(creditCard);
    }

    public Optional<CreditCard>  deleteCreditCardById(long id) {
        Optional<CreditCard> creditCardDeleted =  creditCardRepository.findById(id);
        creditCardRepository.deleteById(id);
        return creditCardDeleted;

    }

    public Optional<CreditCard>  updateCreditCardById(long id, CreditCard creditCard) {
        CreditCard creditCardTemp = creditCardRepository.getById(id);

        if(!Optional.of(creditCard.getNameOnCard()).isEmpty())
            creditCardTemp.setNameOnCard(creditCard.getNameOnCard());

        if(!Optional.of(creditCard.getExpirationDate()).isEmpty())
            creditCardTemp.setExpirationDate(creditCard.getExpirationDate());

        if(!Optional.of(creditCard.getCardNum()).isEmpty())
            creditCardTemp.setCardNum(creditCard.getCardNum());

        if(!Optional.of(creditCard.getCardType()).isEmpty())
            creditCardTemp.setCardType(creditCard.getCardType());

        return Optional.of(creditCardRepository.save(creditCardTemp));
    }

    public Optional<CreditCard> saveCreditCardInfoOfCustomer(CreditCard creditCard, long customer_id) {

       Set<Long> allIds = creditCardRepository.allCreditCardIds();
        long id = new Random().nextLong();
        while(allIds.contains(id)){
            id = new Random().nextLong();
        }
        creditCardRepository.insertCreditCardInfoOfCustomer(id, creditCard.getNameOnCard(), creditCard.getExpirationDate(), creditCard.getCardNum(),
                creditCard.getCardType(),customer_id);
    return Optional.of(creditCard);
    }

    public Optional<List<CreditCard>> getCreditCardsByCustomerId(long customer_id){
        return creditCardRepository.selectCreditCardsByCustomerId(customer_id);
    }

    public Optional<List<CreditCard>> getCreditCardsByNameOnCard(String nameOnCard) {
        return creditCardRepository.findByNameOnCard(nameOnCard);
    }
    public Optional<List<CreditCard>> getCreditCardsByExpirationDate(String expirationDate) {
        return creditCardRepository.findByExpirationDate(expirationDate);
    }
    public Optional<List<CreditCard>> getCreditCardsByCardNum(String cardNum) {
        return creditCardRepository.findByCardNum(cardNum);
    }
    public  Optional<List<CreditCard>> getCreditCardsByCardType(String cardType) {
        return creditCardRepository.findByCardType(cardType);
    }



}

