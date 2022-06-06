package inhafood.inhamall.controller;

import inhafood.inhamall.domain.Article;
import inhafood.inhamall.domain.Comment;
import inhafood.inhamall.domain.Member;
import inhafood.inhamall.domain.Timestamps;
import inhafood.inhamall.exception.NoLoginUserException;
import inhafood.inhamall.service.ArticleService;
import inhafood.inhamall.service.CommentService;
import inhafood.inhamall.service.MemberService;
import inhafood.inhamall.web.ArticleForm;
import inhafood.inhamall.web.CommentForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final ArticleService articleService;
    private final MemberService memberService;
    private final CommentService commentService;

    @PostMapping("/writeComment")
    public String createCommentForm(@RequestParam(value = "id") Long articleId, Model model, @Valid CommentForm form, BindingResult result, HttpServletRequest request, RedirectAttributes re) {

        Long memberId = (Long) request.getSession().getAttribute("id");
        Member findMember = memberService.findOne(memberId);
        Article findArticle = articleService.findOne(articleId);

        Comment comment = new Comment();
        comment.setDescription(form.getDescription());
        comment.setMember(findMember);
        comment.setArticle(findArticle);
        comment.setTimestamps(new Timestamps(LocalDateTime.now(), LocalDateTime.now(), null));

        commentService.save(comment);
        re.addAttribute("id", articleId);
        return "redirect:/articleDetail";
    }

    @GetMapping("/deleteComment")
    public String deleteComment(@RequestParam(value = "commentId") Long commentId, @RequestParam(value = "articleId") Long articleId, RedirectAttributes re) {

        commentService.delete(commentId);

        re.addAttribute("id", articleId);
        return "redirect:articleDetail";
    }

    @GetMapping("/updateComment")
    public String updateComment(@RequestParam(value = "commentId") Long commentId, @RequestParam(value = "articleId") Long articleId, RedirectAttributes re) {

        re.addAttribute("id", articleId);
        return "redirect:articleDetail";
    }

}
