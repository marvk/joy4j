package net.marvk.joy4j.api.ppjoy;

import net.marvk.joy4j.Joy4JException;
import net.marvk.joy4j.api.Digital;
import net.marvk.joy4j.api.Joy4JDevice;

import java.util.Arrays;
import java.util.Objects;

public class PPJoyDevice implements Joy4JDevice {
    private final PPJoy ppJoy;

    private final int[] analog;
    private final double[] analogInput;
    private final byte[] digital;
    private final Digital[] digitalInput;

    public PPJoyDevice(final PPJoy ppJoy) {
        this.analog = new int[ppJoy.numberOfAnalogValues()];
        this.analogInput = new double[ppJoy.numberOfAnalogValues()];
        this.digital = new byte[ppJoy.numberOfDigitalValues()];
        this.digitalInput = new Digital[ppJoy.numberOfDigitalValues()];
        Arrays.fill(this.digitalInput, Digital.UNPRESSED);
        this.ppJoy = ppJoy;
    }

    @Override
    public void setAnalog(final int id, final double value) {
        checkRange(id, numberOfAnalogValues());

        analogInput[id] = value;
        analog[id] = (int) Math.round((ppJoy.analogMaxValue() - ppJoy.analogMinValue()) * value + ppJoy.analogMinValue());
    }

    @Override
    public double getAnalog(final int id) {
        checkRange(id, numberOfAnalogValues());

        return analogInput[id];
    }

    @Override
    public void setDigital(final int id, final Digital value) {
        checkRange(id, numberOfDigitalValues());

        digitalInput[id] = Objects.requireNonNull(value);
        digital[id] = value.getByteValue();
    }

    @Override
    public Digital getDigital(final int id) {
        checkRange(id, numberOfAnalogValues());

        return digitalInput[id];
    }

    @Override
    public void send() throws Joy4JException {
        ppJoy.sendJoystickState(analog, digital);
    }

    @Override
    public boolean isActive() throws Joy4JException {
        return ppJoy.isActive();
    }

    @Override
    public boolean connect() throws Joy4JException {
        return ppJoy.connect();
    }

    @Override
    public int numberOfAnalogValues() {
        return ppJoy.numberOfAnalogValues();
    }

    @Override
    public int numberOfDigitalValues() {
        return ppJoy.numberOfDigitalValues();
    }

    private static void checkRange(final int value, final int max) {
        if (value < 0 && value >= max) {
            throw new IndexOutOfBoundsException(value);
        }
    }
}
