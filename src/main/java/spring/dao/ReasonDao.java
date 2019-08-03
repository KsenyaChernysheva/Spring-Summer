package spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.model.Reason;

public interface ReasonDao extends JpaRepository<Reason, Long> {

}
