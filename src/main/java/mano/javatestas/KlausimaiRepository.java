package mano.javatestas;

import java.util.List; 

import org.springframework.data.repository.CrudRepository;

 
		public interface KlausimaiRepository extends CrudRepository<Klausimai, Integer>{
	
	List<Klausimai> findByPav(String pav);

	Klausimai findAllById(Integer klausimai_id);

	Klausimai findAllByNr(Integer klausimai_nr);

}
