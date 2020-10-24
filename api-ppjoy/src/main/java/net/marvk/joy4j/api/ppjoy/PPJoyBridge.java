package net.marvk.joy4j.api.ppjoy;

import com.sun.jna.Library;
import com.sun.jna.WString;

public interface PPJoyBridge extends Library {
    long openHandle(WString deviceName);

    boolean isHandleValid(long handle);

    int getLastError();

    boolean sendJoystickState(long handle, int[] analog, byte[] digital);

    int numberOfAnalogValues();

    int numberOfDigitalValues();

    long analogMinValue();

    long analogMaxValue();
}
