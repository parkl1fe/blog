package lt.sebpra.demoblog.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import lt.sebpra.demoblog.models.Article;
import lt.sebpra.demoblog.servicies.ArticleService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ArticleController {

    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping()
    public String getAllArticles(@PageableDefault(sort = "articleStats.score", direction = Direction.DESC, size = 3) Pageable pageable, Model model) {
        model.addAttribute("articles", articleService.getAll(pageable));

        return "front-page";
    }

    @GetMapping("/{username}")
    public String getAllArticlesByUser(@PathVariable String username,
                                       @PageableDefault(sort = "articleStats.score",
                                               direction = Direction.DESC) Pageable pageable, Model model) {

        model.addAttribute("articles", articleService.getAllByUser(username, pageable));
        model.addAttribute("acc", username.toUpperCase());

        return "user-articles-page";
    }

    @GetMapping("/{username}/article/{id}")
    public String getUserArticle(@PathVariable String username, @PathVariable Long id,  Model model) {
        model.addAttribute("article", articleService.getArticle(username, id));

        return "single-article-page";
    }

    @GetMapping("/article")
    public String getArticleForm(Model model) {
//        TODO hardcoded for now p.s. upercase later
        model.addAttribute("acc", "RAJ");
        model.addAttribute("article", new Article());

        return "forms/article-form-page";
    }

    @PostMapping("/article/upload")
    public String uploadArticle(@ModelAttribute("article") Article article, @ModelAttribute("file") MultipartFile file, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            return "forms/article-form-page";
        }
        articleService.saveNewArticle(article, file);

        //        TODO hardcoded for now
        return "redirect:/raj";
    }





}
