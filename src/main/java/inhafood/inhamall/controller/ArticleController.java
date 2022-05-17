package inhafood.inhamall.controller;

import inhafood.inhamall.domain.Article;
import inhafood.inhamall.domain.Member;
import inhafood.inhamall.domain.Timestamps;
import inhafood.inhamall.exception.NoLoginUserException;
import inhafood.inhamall.service.ArticleService;
import inhafood.inhamall.service.MemberService;
import inhafood.inhamall.web.ArticleForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final MemberService memberService;

    @GetMapping("/write")
    public String createArticleForm(Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        if (session.getAttribute("loginCheck") == null) {
            throw new NoLoginUserException();
        }

        model.addAttribute("articleForm", new ArticleForm());
        return "article/write";
    }

    @PostMapping("/write")
    public String saveArticle(@Valid ArticleForm form, BindingResult result, HttpServletRequest request) {

        if (result.hasErrors()) {
            return "article/write";
        }

        Article article = new Article();
        article.setTitle(form.getTitle());
        article.setDescription(form.getDescription());
        article.setTimestamps(new Timestamps(LocalDateTime.now(), LocalDateTime.now(), null));

        Long id = (Long) request.getSession().getAttribute("id");
        Member findMember = memberService.findOne(id);
        article.setMember(findMember);

        articleService.save(article);

        return "redirect:/articleList";
    }

    @GetMapping("/articleList")
    public String showArticles(Model model) {


        List<Article> articles = articleService.findAll();

        model.addAttribute("articles", articles);

        return "article/articleList";
    }
}
