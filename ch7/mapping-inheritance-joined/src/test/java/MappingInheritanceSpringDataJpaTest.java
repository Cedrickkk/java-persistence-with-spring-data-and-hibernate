import config.SpringDataConfiguration;
import models.BankAccount;
import models.BillingDetails;
import models.CreditCard;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import repositories.BillingDetailsRepository;
import repositories.CreditCardRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringDataConfiguration.class})
public class MappingInheritanceSpringDataJpaTest {
    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private BillingDetailsRepository<BillingDetails, Long> billingDetailsRepository;

    @Test
    public void storeLoadEntities() {
        CreditCard creditCard = CreditCard.builder()
                .owner("John Smith")
                .cardNumber("123456789")
                .expirationMonth("10")
                .expirationYear("2030")
                .build();
        creditCardRepository.save(creditCard);

        BankAccount bankAccount = BankAccount.builder()
                .owner("Mike Johnson")
                .account("12345")
                .bankName("Delta Bank")
                .swift("BANKXY12")
                .build();
        billingDetailsRepository.save(bankAccount);

        List<BillingDetails> billingDetails = billingDetailsRepository.findAll();
        List<BillingDetails> billingDetailsWithOwner = billingDetailsRepository.findByOwner("John Smith");
        List<CreditCard> creditCards = creditCardRepository.findAll();

        assertAll(
                () -> assertEquals(2, billingDetails.size()),
                () -> assertEquals(1, billingDetailsWithOwner.size()),
                () -> assertEquals("John Smith", billingDetailsWithOwner.get(0).getOwner()),
                () -> assertEquals(1, creditCards.size())
        );
    }
}
