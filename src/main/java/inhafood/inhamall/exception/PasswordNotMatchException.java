package inhafood.inhamall.exception;

public class PasswordNotMatchException extends RuntimeException{
    public static final String message = "패스워드가 일치하지 않습니다.";
    public PasswordNotMatchException() {
        super(message);
    }
}
