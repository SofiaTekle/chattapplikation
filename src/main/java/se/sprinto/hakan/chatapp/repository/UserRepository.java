package se.sprinto.hakan.chatapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.sprinto.hakan.chatapp.model.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    // Hämtar User tillsammans med alla meddelanden (messages) i samma query

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.messages " +
            "WHERE u.username = :username AND u.password = :password")
    User findByUsernameAndPassword(@Param("username") String username,
                                   @Param("password") String password);


}

