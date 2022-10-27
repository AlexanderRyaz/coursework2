package exception;

public class NotValidTaskException extends RuntimeException{
    public NotValidTaskException(String message) {
        super(message);
    }
}
