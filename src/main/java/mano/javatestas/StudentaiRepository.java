package mano.javatestas;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
 
	
		public interface StudentaiRepository extends CrudRepository<Studentai, Integer>{
			
	List<Studentai> findByPav(String pav);

}
 
