package net.marvk.joy4j.api;

import net.marvk.joy4j.Joy4JException;

public interface Joy4JDevice {
    void setDigital(int id, Digital value) throws Joy4JException;

    Digital getDigital(int id);

    void setAnalog(int id, double value) throws Joy4JException;

    double getAnalog(int id);

    void send() throws Joy4JException;

    boolean isActive() throws Joy4JException;

    boolean connect() throws Joy4JException;

    int numberOfAnalogValues();

    int numberOfDigitalValues();
}
