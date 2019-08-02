package spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.model.UserReasonCountry;

import java.util.List;

public interface UserReasonCountryDao extends JpaRepository<UserReasonCountry, Long> {

    void save(Long userId, Long reasonId, String country);

    @Override
    List<UserReasonCountry> findAll();
}
