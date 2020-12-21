package lt.sebpra.demoblog.repositories;

import lt.sebpra.demoblog.models.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    Page<Article> findAllByUser_Username(String username, Pageable pageable);

    Article findByUser_UsernameAndId(String username, Long id);
}
