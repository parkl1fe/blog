package lt.sebpra.demoblog.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import lt.sebpra.demoblog.servicies.ArticleService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ArticleController {
    private static String  TEMP_PATH = "src/main/resources/static/images/user/image/";


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

    @GetMapping("/article")
    public String getArticleForm() {
        return "article-form-page";
    }

    @PostMapping("/article/upload")
    public String uploadArticle(@RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes) {
//        if (file.isEmpty()) {
//            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
//            return "redirect:uploadStatus";
//        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(TEMP_PATH + file.getOriginalFilename());
            Files.write(path, bytes);

//            redirectAttributes.addFlashAttribute("message",
//                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/Sheldon";
    }



}
