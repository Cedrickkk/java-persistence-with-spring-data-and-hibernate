package org.spring.data.sharedprimarykey.repository;

import org.spring.data.sharedprimarykey.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}