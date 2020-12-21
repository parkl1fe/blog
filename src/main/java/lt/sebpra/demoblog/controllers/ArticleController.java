package lt.sebpra.demoblog.controllers;

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
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ArticleController {

    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping()
    public String getAllArticles(@PageableDefault(sort = "articleStats.score", direction = Direction.DESC, size = 5) Pageable pageable, Model model) {
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
    public String getUserArticle(@PathVariable String username, @PathVariable long id,  Model model) {
        model.addAttribute("article", articleService.getArticle(username, id));

        return "single-article-page";
    }

    @GetMapping("/article/new")
    public String getArticleForm(Model model) {
//        TODO hardcoded for now p.s. uppercase later
        model.addAttribute("acc", "RAJ");
        model.addAttribute("article", new Article());

        return "forms/new-article-form";
    }

    @PostMapping("/article/upload")
    public String uploadArticle(@ModelAttribute("article") Article article, @ModelAttribute("file") MultipartFile file, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            return "forms/new-article-form";
        }
        long newArticleId = articleService.saveNewArticle(article, file).getId();

        //        TODO hardcoded for now
        return "redirect:/raj/article/" + newArticleId;
    }

    @GetMapping("/{username}/article/{id}/edit")
    public String getEditForm(@PathVariable String username, @PathVariable long id, Model model) {
        model.addAttribute("article", articleService.getArticle(username, id));

        return "forms/edit-article-form";
    }

    @PostMapping("/{username}/article/{id}/edit")
    public String updateProductName(@PathVariable String username, @PathVariable long id, @ModelAttribute("article") Article article) {
        articleService.updateArticle(article);
        return "redirect:/" + username + "/article/" + id;
    }

    @GetMapping("/{username}/article/{id}/delete")
    public String deleteProductName(@PathVariable String username, @PathVariable long id) {
        articleService.deleteArticle(articleService.getArticle(username, id));
        return "redirect:/" + username;
    }

}
