package spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.model.User;
import spring.model.UserReason;

import java.util.List;

public interface UserReasonDao extends JpaRepository<UserReason, Long> {

    UserReason findByUserAndCountry(User user, String country);

    int countByCountryAndReasonId(String country, long reasonId);

    List<UserReason> findByUserUsername(String username);
}
