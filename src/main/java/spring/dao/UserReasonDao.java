package spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.model.User;
import spring.model.UserReason;

public interface UserReasonDao extends JpaRepository<UserReason, Long> {

    UserReason findByUserAndCountry(User user, String country);

}
