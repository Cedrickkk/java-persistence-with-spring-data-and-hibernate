package org.spring.data.sharedprimarykey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SharedPrimaryKeyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SharedPrimaryKeyApplication.class, args);
    }

}
