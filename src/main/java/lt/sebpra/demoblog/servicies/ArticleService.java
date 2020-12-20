package lt.sebpra.demoblog.servicies;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.transaction.Transactional;
import lt.sebpra.demoblog.models.Article;
import lt.sebpra.demoblog.repositories.ArticleRepository;
import lt.sebpra.demoblog.utils.FileUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ArticleService {
    private static final String UPLOAD_PATH = System.getProperty("user.dir") + "/uploads/image/";

    private final ArticleStatsService articleStatsService;
    private final ArticleRepository articleRepository;
    private final UserService userService;

    public ArticleService(ArticleStatsService articleStatsService, ArticleRepository articleRepository, UserService userService) {
        this.articleStatsService = articleStatsService;
        this.articleRepository = articleRepository;
        this.userService = userService;
    }

    public Page<Article> getAll(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }

    public Page<Article> getAllByUser(String username, Pageable pageable) {
        return articleRepository.findAllByUser_UserName(username, pageable);
    }

    public Article getArticle(String username, long articleId) {
        return articleRepository.findByUser_UserNameAndId(username, articleId);
    }

    @Transactional
    public void saveNewArticle(Article article, MultipartFile file) {

        try {
            // Get the file and save it
            byte[] bytes = file.getBytes();
            String newFileName = new FileUtil().renameToUUID(file.getOriginalFilename());
            Path path = Paths.get(UPLOAD_PATH + newFileName);
            Files.write(path, bytes);

            article.setImageName(newFileName);
//           TODO hardcoded for now
            article.setUser(userService.getUserByUsername("Raj"));
            article.setArticleStats(articleStatsService.createNewArticleStats());
            articleRepository.save(article);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
