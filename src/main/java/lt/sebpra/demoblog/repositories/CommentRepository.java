package lt.sebpra.demoblog.repositories;

import lt.sebpra.demoblog.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
