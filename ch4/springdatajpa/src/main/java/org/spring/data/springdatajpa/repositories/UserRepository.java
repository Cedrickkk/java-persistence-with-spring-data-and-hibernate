package org.spring.data.springdatajpa.repositories;

import org.spring.data.springdatajpa.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
