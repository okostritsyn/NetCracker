package ua.edu.sumdu.j2se.kostrytsyn.tasks.notifications;

public interface Notification {
    void displayMessage(String title, String message);
    void init();
    void close();
}
