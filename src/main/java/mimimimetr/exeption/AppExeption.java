package mimimimetr.exeption;

public class AppExeption extends Exception {

    public AppExeption() {
        super();
    }

    public AppExeption(final String message) {
        super(message);
    }

    public AppExeption(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AppExeption(final Throwable cause) {
        super(cause);
    }

    protected AppExeption(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
