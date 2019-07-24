package spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.model.Reason;

import java.util.List;

public interface ReasonDao extends JpaRepository<Reason, Long> {

    @Override
    List<Reason> findAll();
}
