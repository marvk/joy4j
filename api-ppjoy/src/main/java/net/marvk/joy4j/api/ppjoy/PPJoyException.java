package net.marvk.joy4j.api.ppjoy;

import net.marvk.joy4j.Joy4JException;

import java.io.Serial;

public class PPJoyException extends Joy4JException {
    @Serial
    private static final long serialVersionUID = -6441036641678232977L;

    public PPJoyException() {
    }

    public PPJoyException(final String message) {
        super(message);
    }

    public PPJoyException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public PPJoyException(final Throwable cause) {
        super(cause);
    }

    public PPJoyException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
