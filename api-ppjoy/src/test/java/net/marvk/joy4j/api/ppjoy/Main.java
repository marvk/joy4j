package net.marvk.joy4j.api.ppjoy;

import com.sun.jna.Native;
import net.marvk.joy4j.Joy4JException;
import net.marvk.joy4j.api.Digital;
import net.marvk.joy4j.api.Joy4JDevice;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Paths;

public final class Main {
    private Main() {
        throw new AssertionError("No instances of main class " + Main.class);
    }

    private static final String PPJOY_1 = "\\\\.\\PPJoyIOCTL2";

    public static void main(final String[] args) throws Joy4JException {
        System.setProperty("jna.library.path", Paths.get("D:\\20 Programming\\VisualStudioProjects\\PPJoyBridge\\x64\\Release")
                                                    .toAbsolutePath()
                                                    .toString());

        final PPJoyBridge bridge = Native.load("PPJoyBridge.dll", PPJoyBridge.class);

        final PPJoy ppJoyNativeWrapper = new PPJoyNativeWrapper(bridge, PPJOY_1);

        final Joy4JDevice device = new PPJoyDevice(ppJoyNativeWrapper);

        device.connect();

        final JPanel sliderPanel = new JPanel();
        sliderPanel.setLayout(new GridLayout(9, 2));
        for (int i = 0; i < 9; i++) {
            addRow(device, sliderPanel, i);
        }

        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(9, 4));
        for (int i = 0; i < 36; i++) {
            addButton(device, buttonPanel, i);
        }

        final JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(2, 1));
        jPanel.add(sliderPanel);
        jPanel.add(buttonPanel);

        final JFrame frame = new JFrame();
        frame.add(jPanel);
        frame.pack();
        frame.setVisible(true);
    }

    private static void addButton(final Joy4JDevice device, final JPanel panel, final int i) {
        final JToggleButton button = new JToggleButton(String.valueOf(i + 1));
        button.addActionListener(ignored -> {
            try {
                device.setDigital(i, Digital.of(button.isSelected()));
                device.send();
            } catch (Joy4JException e) {
                e.printStackTrace();
            }
        });
        panel.add(button);
    }

    private static void addRow(final Joy4JDevice device, final JPanel panel, final int id) {
        panel.add(new JLabel(String.valueOf(1 + id)));
        panel.add(createSlider(device, id));
    }

    private static JSlider createSlider(final Joy4JDevice device, final int id) {
        final JSlider slider1 = new JSlider(0, 100000);
        addListener(device, slider1, id);
        return slider1;
    }

    private static void addListener(final Joy4JDevice device, final JSlider slider, final int id) {
        slider.addChangeListener(ignored -> {
            try {
                device.setAnalog(id, slider.getValue() / 100000.);
                device.send();
            } catch (Joy4JException e) {
                e.printStackTrace();
            }
        });
    }
}
