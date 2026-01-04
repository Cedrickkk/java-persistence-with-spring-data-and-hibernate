package org.spring.data.sharedprimarykey.repository;

import org.spring.data.sharedprimarykey.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}