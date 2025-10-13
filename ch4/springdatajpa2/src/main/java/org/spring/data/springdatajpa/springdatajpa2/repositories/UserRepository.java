package org.spring.data.springdatajpa.springdatajpa2.repositories;

import org.spring.data.springdatajpa.springdatajpa2.models.Projection;
import org.spring.data.springdatajpa.springdatajpa2.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Streamable;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    List<User> findAllByOrderByUsernameAsc();

    List<User> findByRegistrationDateBetween(LocalDate start, LocalDate end);

    List<User> findByUsernameAndEmail(String username, String email);

    List<User> findByUsernameOrEmail(String username, String email);

    List<User> findByUsernameIgnoreCase(String username);

    List<User> findByLevelOrderByUsernameDesc(int level);

    List<User> findByLevelGreaterThanEqual(int level);

    List<User> findByUsernameContaining(String text);

    List<User> findByUsernameLike(String text);

    List<User> findByUsernameStartingWith(String start);

    List<User> findByUsernameEndingWith(String end);

    List<User> findByActive(boolean active);

    List<User> findByRegistrationDateIn(Collection<LocalDate> dates);

    List<User> findByRegistrationDateNotIn(Collection<LocalDate> dates);

    User findFirstByOrderByUsernameAsc();

    User findTopByOrderByRegistrationDateDesc();

    List<User> findFirst2ByLevel(int level, Sort sort);

    List<User> findFirst2ByLevelOrderByRegistrationDate(int level);

    List<User> findByLevel(int level, Sort sort);

    List<User> findByActive(boolean active, Pageable pageable);

    Streamable<User> findByEmailContaining(String text);

    Streamable<User> findByLevel(int level);

    @Query("SELECT COUNT(u) FROM User u WHERE u.active = :active")
    int findNumberOfUserByActivity(@Param("active") boolean active);

    @Query("SELECT u FROM User u WHERE u.level = :level AND u.active = :active")
    List<User> findByLevelAndActive(@Param("level") int level,
                                    @Param("active") boolean active);

    @Query(value = "SELECT COUNT(*) FROM users u WHERE u.active = :active", nativeQuery = true)
    int findNumberOfUserByActivityNative(boolean active);

    @Query("SELECT u.username, LENGTH(u.email) AS email_length " +
            "FROM #{#entityName} u WHERE u.username LIKE %:text%")
    List<Object[]> findByAsArrayAndSort(@Param("text") String text, Sort sort);

    List<Projection.UserSummary> findByRegistrationDateAfter(LocalDate date);

    List<Projection.UsernameOnly> findByEmail(String email);

    <T> List<T> findByEmail(String email, Class<T> type);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.level = :newVal WHERE u.level = :oldVal")
    int updateLevel(@Param("oldVal") int oldVal, @Param("newVal") int newVal);

    @Transactional
    int deleteByLevel(int level);

    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.level = :level")
    int deleteBulkByLevel(@Param("level") int level);
}
