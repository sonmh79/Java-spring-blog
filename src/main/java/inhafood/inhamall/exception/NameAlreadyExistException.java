package inhafood.inhamall.exception;

public class NameAlreadyExistException extends RuntimeException {
    private static final String message = "이름이 중복됩니다.";

    public NameAlreadyExistException() {
        super(message);
    }
}
