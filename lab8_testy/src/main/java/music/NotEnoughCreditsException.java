package music;

public class NotEnoughCreditsException extends Exception {
    public NotEnoughCreditsException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "NotEnoughCreditsException: " + getMessage();
    }
}
