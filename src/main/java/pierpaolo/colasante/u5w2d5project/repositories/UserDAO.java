package pierpaolo.colasante.u5w2d5project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pierpaolo.colasante.u5w2d5project.entities.User;

@Repository
public interface UserDAO  extends JpaRepository<User, Long> {
}
