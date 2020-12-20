package lt.sebpra.demoblog.repositories;

import java.util.List;
import lt.sebpra.demoblog.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByArticle_Id(Long id);

}
