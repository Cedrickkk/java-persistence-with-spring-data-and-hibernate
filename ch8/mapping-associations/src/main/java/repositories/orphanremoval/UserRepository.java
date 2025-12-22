package repositories.orphanremoval;

import onetomany.orphanremoval.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u INNER JOIN FETCH u.bids WHERE u.id = :id")
    User findUserWithBids(@Param("id") Long id);

}
