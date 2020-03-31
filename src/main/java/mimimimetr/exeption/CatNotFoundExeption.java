package mimimimetr.exeption;

public class CatNotFoundExeption extends AppExeption {

    public CatNotFoundExeption() {
        super();
    }

    public CatNotFoundExeption(final String message) {
        super(message);
    }

    public CatNotFoundExeption(final String message, final Throwable cause) {
        super(message, cause);
    }

    public CatNotFoundExeption(final Throwable cause) {
        super(cause);
    }

    protected CatNotFoundExeption(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
