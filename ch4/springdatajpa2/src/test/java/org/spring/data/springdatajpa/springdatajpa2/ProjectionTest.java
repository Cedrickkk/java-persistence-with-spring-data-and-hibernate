package org.spring.data.springdatajpa.springdatajpa2;

import org.junit.jupiter.api.Test;
import org.spring.data.springdatajpa.springdatajpa2.models.Projection;
import org.spring.data.springdatajpa.springdatajpa2.models.User;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectionTest extends SpringDataJpa2ApplicationTests {
    @Test
    void testProjectionUsername() {
        List<Projection.UsernameOnly> users = userRepository.findByEmail("hula@nm0@gmail.com");

        assertAll(
                () -> assertEquals(1, users.size()),
                () -> assertEquals("hula@nm0", users.get(0).getUsername())
        );
    }

    @Test
    void testProjectionUserSummary() {
        List<Projection.UserSummary> users = userRepository.findByRegistrationDateAfter(
                LocalDate.of(2025, Month.FEBRUARY, 1));

        assertAll(
                () -> assertEquals(1, users.size()),
                () -> assertEquals("hula@nm0", users.get(0).getUsername()),
                () -> assertEquals("hula@nm0 hula@nm0@gmail.com", users.get(0).getInfo())
        );
    }

    @Test
    void testDynamicProjection() {
        List<Projection.UsernameOnly> usernames =
                userRepository.findByEmail("hula@nm0@gmail.com", Projection.UsernameOnly.class);

        List<User> users = userRepository.findByEmail("hula@nm0@gmail.com", User.class);

        assertAll(
                () -> assertEquals(1, usernames.size()),
                () -> assertEquals("hula@nm0", usernames.get(0).getUsername()),
                () -> assertEquals(1, users.size()),
                () -> assertEquals("hula@nm0", users.get(0).getUsername())
        );
    }
}
