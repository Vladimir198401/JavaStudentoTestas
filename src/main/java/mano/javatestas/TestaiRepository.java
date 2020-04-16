package mano.javatestas;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

public interface TestaiRepository extends CrudRepository<Testai, Integer>{

	ArrayList<Testai> findByStudentaiId(Integer studentai_id);

}
