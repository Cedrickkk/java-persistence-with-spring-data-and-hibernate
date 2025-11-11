import config.SpringDataConfiguration;
import models.BankAccount;
import models.CreditCard;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import repositories.BankAccountRepository;
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
    private BankAccountRepository bankAccountRepository;

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
        bankAccountRepository.save(bankAccount);

        List<CreditCard> creditCardsWithOwner = creditCardRepository.findByOwner("John Smith");
        List<BankAccount> bankAccountsWithOwner = bankAccountRepository.findByOwner("Mike Johnson");

        List<CreditCard> creditCardsWithCardNumber = creditCardRepository.findByCardNumber("123456789");
        List<BankAccount> bankAccountsWithSwift = bankAccountRepository.findBySwift("BANKXY12");

        assertAll(
                () -> assertEquals(1, creditCardsWithOwner.size()),
                () -> assertEquals("123456789", creditCardsWithOwner.get(0).getCardNumber()),
                () -> assertEquals(1, bankAccountsWithOwner.size()),
                () -> assertEquals("12345", bankAccountsWithOwner.get(0).getAccount()),
                () -> assertEquals(1, creditCardsWithCardNumber.size()),
                () -> assertEquals("John Smith", creditCardsWithCardNumber.get(0).getOwner()),
                () -> assertEquals(1, bankAccountsWithSwift.size()),
                () -> assertEquals("Mike Johnson", bankAccountsWithSwift.get(0).getOwner())
        );
    }
}
