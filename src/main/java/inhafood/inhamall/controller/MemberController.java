package inhafood.inhamall.controller;

import inhafood.inhamall.domain.Member;
import inhafood.inhamall.service.MemberService;
import inhafood.inhamall.web.signinForm;
import inhafood.inhamall.web.signupForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/")
    public String home(@RequestParam(value = "errorMessage", required = false) String errorMessage, Model model, HttpSession session) {

        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
        }

        model.addAttribute("data", session.getAttribute("loginCheck"));
        return "index";
    }

    @GetMapping("/signin")
    public String createSigninForm(@RequestParam(value = "errorMessage",required = false) String errorMessage, Model model) {
        model.addAttribute("signinForm", new signinForm());
        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
        }
        return "members/signin";
    }

    @PostMapping("/signin")
    @ExceptionHandler(value = IllegalStateException.class)
    public String login(@Valid signinForm form, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            return "members/signin";
        }
        HttpSession session = request.getSession();
        String loginId = form.getLoginId();
        String pw = form.getPassword();
        Member findMember = memberService.loginCheck(loginId, pw);
        session.setAttribute("loginCheck", true);
        session.setAttribute("id", findMember.getId());
        session.setAttribute("loginId", findMember.getLoginId());
        session.setAttribute("name", findMember.getName());
        return "redirect:/";
    }

    @GetMapping("/signup")
    public String createSignupForm(Model model) {
        model.addAttribute("signupForm", new signupForm());
        return "members/signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid signupForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "members/signup";
        }

        Member member = new Member();
        member.setName(form.getName());
        member.setLoginId(form.getLoginId());
        member.setPassword(form.getPassword());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {

        request.getSession().invalidate();
        request.getSession(true);
        return "redirect:/";
    }



}
