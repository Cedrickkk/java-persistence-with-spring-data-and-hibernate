import models.User;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HibernateBatchingTest extends SpringDataJpaMappingTest {
    @Test
    void testGetUsers() {
        Iterable<User> userIterable = userRepository.findAll();
        
        List<User> users = StreamSupport.stream(userIterable.spliterator(), false)
                .toList();

        assertEquals(100, users.size());
    }
}
