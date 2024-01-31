package org.maj.CityCardCore.repository;

import org.maj.CityCardCore.model.Ticket;
import org.maj.CityCardCore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {


    @Query("SELECT s FROM Ticket s where s.passenger.id = :X")
    List<Ticket> getAllTickets(@Param("X") long X);

    @Query("select u from User u where u.login=:login and u.password=:password")
    User findByLoginAndCheckIfPasswordIsCorrect(@Param("login") String login, @Param("password") String password);
}
