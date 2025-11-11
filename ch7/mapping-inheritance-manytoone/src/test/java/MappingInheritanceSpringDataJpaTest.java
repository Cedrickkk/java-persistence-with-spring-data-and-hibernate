import config.SpringDataConfiguration;
import models.CreditCard;
import models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import repositories.CreditCardRepository;
import repositories.UserRepository;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringDataConfiguration.class})
public class MappingInheritanceSpringDataJpaTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Test
    public void storeLoadEntities() {
        CreditCard creditCard = CreditCard.builder()
                .owner("John Smith")
                .cardNumber("123456789")
                .expirationMonth("10")
                .expirationYear("2030")
                .build();

        User user = User.builder()
                .username("hula@nmo")
                .defaultBilling(creditCard)
                .build();

        creditCardRepository.save(creditCard);
        userRepository.save(user);

        userRepository.findById(user.getId()).ifPresent((_user) -> {
            System.out.println(_user.getDefaultBilling().getClass().getSimpleName());
            _user.getDefaultBilling().pay(123L);
        });

        assertAll(
                () -> assertEquals("John Smith", user.getDefaultBilling().getOwner())
        );
    }
}
