package net.marvk.joy4j.api.ppjoy;

public interface PPJoy {
    void sendJoystickState(int[] analog, byte[] digital) throws PPJoyException;

    int numberOfAnalogValues();

    int numberOfDigitalValues();

    long analogMinValue();

    long analogMaxValue();

    boolean isActive();

    boolean connect() throws PPJoyException;
}
