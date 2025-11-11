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
public class MappingInheritanceSpringDataJPATest {
    private final CreditCardRepository creditCardRepository;

    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public MappingInheritanceSpringDataJPATest(CreditCardRepository creditCardRepository,
                                               BankAccountRepository bankAccountRepository) {
        this.creditCardRepository = creditCardRepository;
        this.bankAccountRepository = bankAccountRepository;
    }

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

        List<CreditCard> creditCards = creditCardRepository.findByOwner("John Smith");
        List<BankAccount> bankAccounts = bankAccountRepository.findByOwner("Mike Johnson");

        List<CreditCard> creditCardsWithExpirationYear = creditCardRepository.findByExpirationYear("2030");
        List<BankAccount> bankAccountsWithSwift = bankAccountRepository.findBySwift("BANKXY12");

        assertAll(
                () -> assertEquals(1, creditCards.size()),
                () -> assertEquals("123456789", creditCards.get(0).getCardNumber()),
                () -> assertEquals(1, bankAccounts.size()),
                () -> assertEquals("12345", bankAccounts.get(0).getAccount()),
                () -> assertEquals(1, creditCardsWithExpirationYear.size()),
                () -> assertEquals("John Smith", creditCardsWithExpirationYear.get(0).getOwner()),
                () -> assertEquals(1, bankAccountsWithSwift.size()),
                () -> assertEquals("Mike Johnson", bankAccountsWithSwift.get(0).getOwner())
        );
    }

}
