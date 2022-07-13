package ua.edu.sumdu.j2se.kostrytsyn.tasks.controller;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.Notification;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.Task;

import java.time.LocalDateTime;
import java.util.concurrent.ScheduledExecutorService;

public class RunTaskController implements Runnable {
    private Task currTask;
    private ScheduledExecutorService scheduler;

    public void setCurrTask(Task task) {
        this.currTask = task;
    }

    @Override
    public void run() {

        if ((LocalDateTime.now().isAfter(currTask.getEndTime()) && currTask.isRepeated()) || !currTask.isActive()) {
            scheduler.shutdownNow();
            return;
        }

        try {
            Notification notificationInTray = Controller.getNotificationTray();
            if (notificationInTray == null) return;
            notificationInTray.displayMessageInTray("You have a new task",currTask.getTitle().trim(),"Task manager");
         } catch (Exception e) {
             System.out.println("An error running thread " + e.getMessage());
         }
    }

    public void setManager(ScheduledExecutorService scheduler) {
        this.scheduler = scheduler;
    }

    public ScheduledExecutorService getManager() {
       return this.scheduler;
    }
}
