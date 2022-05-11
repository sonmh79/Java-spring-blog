package inhafood.inhamall.controller;

import inhafood.inhamall.domain.Member;
import inhafood.inhamall.service.MemberService;
import inhafood.inhamall.web.signinForm;
import inhafood.inhamall.web.signupForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        model.addAttribute("data", session.getAttribute("loginCheck"));
        return "index";
    }

    @GetMapping("/signin")
    public String createSigninForm(Model model) {
        model.addAttribute("signinForm", new signinForm());
        return "members/signin";
    }

    @PostMapping("/signin")
    public String login(@Valid signinForm form, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            return "members/signin";
        }
        String loginId = form.getLoginId();
        String pw = form.getPassword();
        if (memberService.loginCheck(loginId, pw)) {
            session.setAttribute("loginCheck", true);
            session.setAttribute("loginId", loginId);
            return "redirect:/";
        }

        return "members/signin";
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
    public String logout(HttpSession session) {

        Object sessionStatus = session.getAttribute("loginCheck");
        session.setAttribute("loginCheck", null);
        session.setAttribute("loginId", null);

        return "redirect:/";
    }



}
