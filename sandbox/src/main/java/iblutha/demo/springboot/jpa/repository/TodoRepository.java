package iblutha.demo.springboot.jpa.repository;

import iblutha.demo.springboot.jpa.domain.JpaTodo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by tibo on 07/11/2015.
 */
public interface TodoRepository extends JpaRepository<JpaTodo,Long> {

}
