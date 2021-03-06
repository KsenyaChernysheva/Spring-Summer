package spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.model.User;

public interface UserDao extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
