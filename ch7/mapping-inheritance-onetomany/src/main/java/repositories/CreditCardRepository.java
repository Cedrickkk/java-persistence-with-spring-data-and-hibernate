package repositories;

import models.CreditCard;

import java.util.List;

public interface CreditCardRepository extends BillingDetailsRepository<CreditCard, Long> {
    List<CreditCard> findByCardNumber(String cardNumber);
}
