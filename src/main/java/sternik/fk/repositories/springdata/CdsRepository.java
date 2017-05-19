package sternik.fk.repositories.springdata;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sternik.fk.entities.Cd;



@Repository
public interface CdsRepository  extends JpaRepository<Cd, Integer> {
	public Cd findById(Long id);
}