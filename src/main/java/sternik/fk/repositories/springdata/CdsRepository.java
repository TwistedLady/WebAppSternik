package sternik.fk.repositories.springdata;


import sternik.fk.entities.Status;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sternik.fk.entities.Cd;
import sternik.fk.entities.Status;



@Repository
public interface CdsRepository  extends JpaRepository<Cd, Integer> {
	public Cd findById(Long id);
	public List<Cd> findByStatus(Status status);
}