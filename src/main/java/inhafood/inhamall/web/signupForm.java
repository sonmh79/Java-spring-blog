package inhafood.inhamall.web;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class signupForm {

    @NotEmpty(message = "아이디는 필수입니다.")
    private String loginId;
    @NotEmpty(message = "패스워드는 필수입니다.")
    @Min(message = "최소 2자 이상 입력하세요", value = 2)
    private String password;
    @NotEmpty(message = "이름은 필수입니다.")
    private String name;
}
