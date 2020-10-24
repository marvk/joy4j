package net.marvk.joy4j.api;

public enum Digital {
    UNPRESSED((byte) 0), PRESSED((byte) 1);

    private final byte byteValue;

    Digital(final byte c) {
        this.byteValue = c;
    }

    public byte getByteValue() {
        return byteValue;
    }

    public static Digital of(final boolean b) {
        if (b) {
            return PRESSED;
        } else {
            return UNPRESSED;
        }
    }

    public static Digital of(final int i) {
        return of(i != 0);
    }

    public Digital opposite() {
        return switch (this) {
            case PRESSED -> UNPRESSED;
            case UNPRESSED -> PRESSED;
        };
    }
}
