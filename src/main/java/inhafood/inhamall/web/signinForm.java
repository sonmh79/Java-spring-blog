package inhafood.inhamall.web;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class signinForm {

    @NotEmpty(message = "아이디를 입력하세요.")
    private String loginId;

    @NotEmpty(message = "패스워드를 입력하세요.")
    private String password;
}
