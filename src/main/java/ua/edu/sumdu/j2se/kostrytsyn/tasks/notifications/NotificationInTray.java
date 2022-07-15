package ua.edu.sumdu.j2se.kostrytsyn.tasks.notifications;

import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.TrayIcon.MessageType;

public class NotificationInTray  implements Notification {
    private SystemTray tray;
    private TrayIcon trayIcon;

    final static Logger logger = Logger.getLogger(NotificationInTray.class);

    public void init(){
        this.tray = SystemTray.getSystemTray();
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        trayIcon = new TrayIcon(image, "Task manager");
        trayIcon.setImageAutoSize(true);
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            logger.error("Error adding icon in tray", e);
            e.printStackTrace();
        }
    }

    public void close() {
        tray.remove(trayIcon);
    }

    public void displayMessage(String title, String message) {
        String toolTip = "Task manager";
        if (SystemTray.isSupported()) {
            try {
                displayTray(title, message, toolTip);
            } catch (AWTException e) {
                logger.error("Error while connecting to tray", e);
                e.printStackTrace();
            }
        } else {
            logger.error("System tray not supported!");
        }
    }

    private void displayTray(String title, String message, String toolTip) throws AWTException {
        trayIcon.setToolTip(toolTip);
        trayIcon.displayMessage(title, message, MessageType.INFO);
    }
}


