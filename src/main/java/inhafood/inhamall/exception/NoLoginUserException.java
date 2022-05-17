package inhafood.inhamall.exception;

public class NoLoginUserException extends RuntimeException{
    private static final String message = "로그인이 필요합니다.";

    public NoLoginUserException() {
        super(message);
    }
}
