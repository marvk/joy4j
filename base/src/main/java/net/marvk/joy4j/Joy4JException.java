package net.marvk.joy4j;

import java.io.Serial;

public class Joy4JException extends Exception {
    @Serial
    private static final long serialVersionUID = -846224561390833536L;

    public Joy4JException() {
    }

    public Joy4JException(final String message) {
        super(message);
    }

    public Joy4JException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public Joy4JException(final Throwable cause) {
        super(cause);
    }

    public Joy4JException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
