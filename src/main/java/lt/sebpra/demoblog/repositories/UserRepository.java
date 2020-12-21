package lt.sebpra.demoblog.repositories;

import java.util.Optional;
import lt.sebpra.demoblog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByUsername(String username);

    Optional<User> findByUsername(String username);
}
