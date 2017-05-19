package sternik.fk.services;



import java.util.List;
import java.util.Optional;

import sternik.fk.entities.Cd;

public interface KlasterService {
	List<Cd> findAll();
	List<Cd> findAllToSell();
	
	Optional<Cd> findById(Long id);
    Optional<Cd> create(Cd cd);
    Optional<Cd> edit(Cd cd);
    Optional<Boolean> deleteById(Long id);

    List<Cd> findLatest3();
}
