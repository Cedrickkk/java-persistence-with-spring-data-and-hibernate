import config.SpringDataConfiguration;
import models.BankAccount;
import models.BillingDetails;
import models.CreditCard;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import repositories.BankAccountRepository;
import repositories.BillingDetailsRepository;
import repositories.CreditCardRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringDataConfiguration.class})
public class MappingInheritanceSpringDataJpaTest {
    @Autowired
    private BillingDetailsRepository<BillingDetails, Long> billingDetailsRepository;
    @Autowired
    private CreditCardRepository creditCardRepository;
    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Test
    public void storeLoadEntities() {
        CreditCard creditCard = CreditCard.builder()
                .owner("John Smith")
                .cardNumber("123456789")
                .expirationMonth("10")
                .expirationYear("2030")
                .build();
        billingDetailsRepository.save(creditCard);

        BankAccount bankAccount = BankAccount.builder()
                .owner("Mike Johnson")
                .account("12345")
                .bankName("Delta Bank")
                .swift("BANKXY12")
                .build();
        billingDetailsRepository.save(bankAccount);

        // Using concrete repo to save an entity (RECOMMENDED APPROACH)
        BankAccount _bankAccount = BankAccount.builder()
                .owner("John Smith")
                .account("1234")
                .bankName("MariBank")
                .swift("MARIXY12")
                .build();
        bankAccountRepository.save(_bankAccount);

        List<BillingDetails> billingDetails = billingDetailsRepository.findAll();
        List<BillingDetails> billingDetailsWithOwner = billingDetailsRepository.findByOwner("John Smith");

        List<CreditCard> creditCards = creditCardRepository.findAll();
        List<BankAccount> bankAccounts = bankAccountRepository.findAll();

        assertAll(
                () -> assertEquals(3, billingDetails.size()),
                () -> assertEquals(2, billingDetailsWithOwner.size()),
                () -> assertEquals(2, bankAccounts.size()),
                () -> assertEquals("John Smith", billingDetailsWithOwner.get(0).getOwner()),
                () -> assertEquals(1, creditCards.size())
        );
    }
}
