package inhafood.inhamall.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchLoginIdException.class)
    public String handleNoSuchLoginIdException(NoSuchLoginIdException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "redirect:/signin";
    }

    @ExceptionHandler(PasswordNotMatchException.class)
    public String handlePasswordNotMatchException(PasswordNotMatchException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "redirect:/signin";
    }

    @ExceptionHandler(NoLoginUserException.class)
    public String handleNoLoginUserException(NoLoginUserException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "redirect:/";
    }

    @ExceptionHandler(LoginIdAlreadyExistException.class)
    public String handleLoginIdAlreadyExistException(LoginIdAlreadyExistException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "redirect:/signup";
    }

    @ExceptionHandler(NameAlreadyExistException.class)
    public String handleNameAlreadyExistException(NameAlreadyExistException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "redirect:/signup";
    }
}
