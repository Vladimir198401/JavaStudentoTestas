package mano.javatestas;

import java.util.List; 

import org.springframework.data.repository.CrudRepository;

	public interface AtsakymaiRepository extends CrudRepository<Atsakymai, Integer>{
		
		List<Atsakymai> findByPav(String pav);
		
		//Atsakymai findByPav(String pav);	
		
		List<Atsakymai> findByKlausimaiId(Integer klausimai_id);

 }
