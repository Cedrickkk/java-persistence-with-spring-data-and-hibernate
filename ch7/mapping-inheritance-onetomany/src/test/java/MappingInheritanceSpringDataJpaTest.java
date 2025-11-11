import config.SpringDataConfiguration;
import models.BankAccount;
import models.CreditCard;
import models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import repositories.BankAccountRepository;
import repositories.CreditCardRepository;
import repositories.UserRepository;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringDataConfiguration.class})
public class MappingInheritanceSpringDataJpaTest {
    @Autowired
    private UserRepository userRepository;

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

        BankAccount bankAccount = BankAccount.builder()
                .owner("John Smith")
                .account("123456")
                .bankName("Delta Bank")
                .swift("BANKXY12")
                .build();

        User user = User.builder()
                .username("hula@nmo")
                .defaultBilling(creditCard)
                .billingDetails(new HashSet<>(Set.of(creditCard, bankAccount)))
                .build();

        creditCardRepository.save(creditCard);
        bankAccountRepository.save(bankAccount);
        userRepository.save(user);

        assertAll(
                () -> assertEquals("John Smith", user.getDefaultBilling().getOwner()),
                () -> assertEquals(2, user.getBillingDetails().size())
        );

        System.out.println(user.getBillingDetails());
    }
}
