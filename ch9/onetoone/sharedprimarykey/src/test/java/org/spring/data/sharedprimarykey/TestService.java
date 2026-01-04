package org.spring.data.sharedprimarykey;

import org.spring.data.sharedprimarykey.domain.Address;
import org.spring.data.sharedprimarykey.domain.User;
import org.spring.data.sharedprimarykey.repository.AddressRepository;
import org.spring.data.sharedprimarykey.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Service
public class TestService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Transactional
    public void storeLoadEntities() {
        Address address = Address.builder()
                .street("F. Manalo")
                .zipCode("1600")
                .city("Pasig")
                .build();
        addressRepository.save(address);

        User cedric = User.builder()
                .id(address.getId())
                .name("John Cedric Panti")
                .shippingAddress(address)
                .build();
        userRepository.save(cedric);

        User user = userRepository.findById(cedric.getId()).get();
        Address userAddress = addressRepository.findById(address.getId()).get();

        assertAll(
                () -> assertEquals("F. Manalo", user.getShippingAddress().getStreet()),
                () -> assertEquals("1600", user.getShippingAddress().getZipCode()),
                () -> assertEquals("Pasig", user.getShippingAddress().getCity()),
                () -> assertEquals("F. Manalo", userAddress.getStreet()),
                () -> assertEquals("1600", userAddress.getZipCode()),
                () -> assertEquals("Pasig", userAddress.getCity())
        );

    }

}
