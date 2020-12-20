package lt.sebpra.demoblog.servicies;

import java.util.List;
import javax.transaction.Transactional;
import lt.sebpra.demoblog.models.Comment;
import lt.sebpra.demoblog.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getCommentsForArticle(Long id) {
        return commentRepository.findAllByArticle_Id(id);
    }

    @Transactional
    public void deleteArticleComments(Long articleId) {
        commentRepository.deleteAll(getCommentsForArticle(articleId));
    }
}
