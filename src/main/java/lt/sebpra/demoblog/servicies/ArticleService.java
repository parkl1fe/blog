package lt.sebpra.demoblog.servicies;

import lt.sebpra.demoblog.models.Article;
import lt.sebpra.demoblog.repositories.ArticleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    private ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Page<Article> getAll(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }

    public Page<Article> getAllByUser(String username, Pageable pageable) {
        return articleRepository.findAllByUser_UserName(username, pageable);
    }

    public Article getArticle(String username, Long articeId) {
        return articleRepository.findByUser_UserNameAndId(username, articeId);
    }
}
