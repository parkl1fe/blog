package lt.sebpra.demoblog.servicies;

import javax.transaction.Transactional;
import lt.sebpra.demoblog.models.ArticleStats;
import lt.sebpra.demoblog.repositories.ArticleStatsRepository;
import org.springframework.stereotype.Service;

@Service
public class ArticleStatsService {

    private ArticleStatsRepository articleStatsRepository;

    public ArticleStatsService(ArticleStatsRepository articleStatsRepository) {
        this.articleStatsRepository = articleStatsRepository;
    }

    @Transactional
    public ArticleStats createNewArticleStats() {
        return articleStatsRepository.save(
                ArticleStats.builder()
                        .score(3500)
                        .views(0)
                        .build());
    }

    @Transactional
    public void deleteArticleStats(Long articleId) {
        articleStatsRepository.deleteById(articleId);
    }
}
