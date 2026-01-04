package org.spring.data.sharedprimarykey;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SharedPrimaryKeyApplicationTests {

    @Autowired
    TestService testService;

    @Test
    void testStoreLoadEntities() {
        testService.storeLoadEntities();
    }

}
