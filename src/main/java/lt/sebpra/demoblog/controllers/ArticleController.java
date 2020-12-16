package lt.sebpra.demoblog.controllers;

import lt.sebpra.demoblog.servicies.ArticleService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ArticleController {

    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping()
    public String getAllArticles(@PageableDefault(sort = "articleStats.score", direction = Direction.DESC) Pageable pageable, Model model) {
        model.addAttribute("articles", articleService.getAll(pageable));
        return "front-page";
    }

    @GetMapping("/{username}")
    public String getAllArticlesByUser(@PathVariable String username, @PageableDefault(sort = "articleStats.score", direction = Direction.DESC) Pageable pageable, Model model) {
        model.addAttribute("articles", articleService.getAllByUser(username, pageable));
        return "user-articles-page";
    }

    @GetMapping("/{username}/article/{id}")
    public String getUserArticle(@PathVariable String username, @PathVariable Long id,  Model model) {
        model.addAttribute("article", articleService.getArticle(username, id));
        return "single-article-page";
    }



}
