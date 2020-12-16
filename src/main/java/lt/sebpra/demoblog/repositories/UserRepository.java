package lt.sebpra.demoblog.repositories;

import lt.sebpra.demoblog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
