package placementorg.projectt.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import placementorg.projectt.Entity.User;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Integer> {


    Optional<placementorg.projectt.Entity.User> findByUsername(String username);

}
