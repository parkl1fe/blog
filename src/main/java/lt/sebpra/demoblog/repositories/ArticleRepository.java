package lt.sebpra.demoblog.repositories;

import lt.sebpra.demoblog.models.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    Page<Article> findAllByUser_UserName(String username, Pageable pageable);

    Article findByUser_UserNameAndId(String username, Long id);
}
