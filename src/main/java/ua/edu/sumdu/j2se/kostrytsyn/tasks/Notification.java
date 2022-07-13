package ua.edu.sumdu.j2se.kostrytsyn.tasks;

import java.awt.*;
import java.awt.TrayIcon.MessageType;

public class Notification {
        private SystemTray tray;
        private TrayIcon trayIcon;

    public Notification() {
        this.tray = SystemTray.getSystemTray();
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        trayIcon = new TrayIcon(image, "Task manager");
        trayIcon.setImageAutoSize(true);
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void closeTray() {
        tray.remove(trayIcon);
    }

    public void displayMessageInTray(String title, String message, String toolTip) {
            if (SystemTray.isSupported()) {
                try {
                    displayTray(title,message,toolTip);
                } catch (AWTException e) {
                    System.err.println("Error while connecting to tray");
                    e.printStackTrace();
                }
            } else {
                System.err.println("System tray not supported!");
            }
    }

        private void displayTray(String title,String message,String toolTip) throws AWTException {
            trayIcon.setToolTip(toolTip);
            trayIcon.displayMessage(title, message, MessageType.INFO);
        }
}


