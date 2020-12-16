package lt.sebpra.demoblog.repositories;

import lt.sebpra.demoblog.models.ArticleStats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleStatsRepository extends JpaRepository<ArticleStats, Long> {
}
