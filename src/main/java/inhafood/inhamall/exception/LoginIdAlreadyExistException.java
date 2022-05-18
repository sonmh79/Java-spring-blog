package inhafood.inhamall.exception;

public class LoginIdAlreadyExistException extends RuntimeException {
    private static final String message = "로그인 아이디가 중복됩니다.";

    public LoginIdAlreadyExistException() {
        super(message);
    }
}
