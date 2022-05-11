package inhafood.inhamall.exception;

public class NoSuchLoginIdException extends RuntimeException {
    public static final String message = "일치하는 아이디가 없습니다.";
    public NoSuchLoginIdException() {
        super(message);
    }
}
