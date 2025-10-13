package org.spring.data.springdatajpa.springdatajpa2;

import org.junit.jupiter.api.Test;
import org.spring.data.springdatajpa.springdatajpa2.models.User;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteQueryTest extends SpringDataJpa2ApplicationTests {
    @Test
    void testDeleteByLevel() {
        int deleted = userRepository.deleteByLevel(2);
        List<User> users = userRepository.findByLevel(2, Sort.by("username"));

        assertEquals(0, users.size());
    }

    @Test
    void testDeleteBulkByLevel() {
        userRepository.deleteBulkByLevel(2);
        List<User> users = userRepository.findByLevel(2, Sort.by("username"));

        assertEquals(0, users.size());
    }
}
