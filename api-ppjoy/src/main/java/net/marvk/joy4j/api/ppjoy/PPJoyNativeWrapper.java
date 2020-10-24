package net.marvk.joy4j.api.ppjoy;

import com.sun.jna.WString;

public class PPJoyNativeWrapper implements PPJoy {
    private static final long INVALID_HANDLE = 0xFFFFFFFFFFFFFFFFL;
    private final PPJoyBridge ppJoyBridge;
    private final String deviceName;

    private long handle = INVALID_HANDLE;

    public PPJoyNativeWrapper(final PPJoyBridge ppJoyBridge, final String deviceName) {
        this.ppJoyBridge = ppJoyBridge;
        this.deviceName = deviceName;
    }

    @Override
    public void sendJoystickState(final int[] analog, final byte[] digital) {
        ppJoyBridge.sendJoystickState(handle, analog, digital);
    }

    @Override
    public int numberOfAnalogValues() {
        return ppJoyBridge.numberOfAnalogValues();
    }

    @Override
    public int numberOfDigitalValues() {
        return ppJoyBridge.numberOfDigitalValues();
    }

    @Override
    public long analogMinValue() {
        return ppJoyBridge.analogMinValue();
    }

    @Override
    public long analogMaxValue() {
        return ppJoyBridge.analogMaxValue();
    }

    @Override
    public boolean isActive() {
        return ppJoyBridge.isHandleValid(handle);
    }

    @Override
    public boolean connect() throws PPJoyException {
        final long maybeHandle = ppJoyBridge.openHandle(new WString(deviceName));

        if (ppJoyBridge.isHandleValid(maybeHandle)) {
            handle = maybeHandle;
            return true;
        }

        throw new PPJoyException("Failed to create handle");
    }
}
