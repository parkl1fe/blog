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
    private final CommentService commentService;

    public ArticleService(ArticleStatsService articleStatsService, ArticleRepository articleRepository, UserService userService, CommentService commentService) {
        this.articleStatsService = articleStatsService;
        this.articleRepository = articleRepository;
        this.userService = userService;
        this.commentService = commentService;
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
    public Article saveNewArticle(Article article, MultipartFile file) {
        String newFileName = new FileUtil().renameToUUID(file.getOriginalFilename());
        try {
            // Get the file and save it
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_PATH + newFileName);
            Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }

        article.setImageName(newFileName);
//           TODO hardcoded for now
        article.setUser(userService.getUserByUsername("Raj"));
        article.setArticleStats(articleStatsService.createNewArticleStats());

        return articleRepository.save(article);
    }

    @Transactional
    public void updateArticle(Article article) {
        Article savedArticle = articleRepository.getOne(article.getId());

        if (!savedArticle.getTitle().equals(article.getTitle())) {
            savedArticle.setTitle(article.getTitle());
        }
        if (!savedArticle.getDescription().equals(article.getDescription())) {
            savedArticle.setDescription(article.getDescription());
        }
        if (!savedArticle.getContent().equals(article.getContent())) {
            savedArticle.setContent(article.getContent());
        }

    }

    @Transactional
    public void deleteArticle(Article article) {
        commentService.deleteArticleComments(article.getId());
        articleRepository.delete(article);
        articleStatsService.deleteArticleStats(article.getArticleStats().getId());
    }
}
