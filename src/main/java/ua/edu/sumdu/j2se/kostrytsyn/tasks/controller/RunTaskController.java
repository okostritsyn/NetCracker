package ua.edu.sumdu.j2se.kostrytsyn.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.Task;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.notifications.Notification;

import java.time.LocalDateTime;
import java.util.concurrent.ScheduledExecutorService;

public class RunTaskController implements Runnable {
    private Task currTask;
    private ScheduledExecutorService scheduler;
    final static Logger logger = Logger.getLogger(RunTaskController.class);

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
            Notification notification = Controller.getNotification();
            if (notification == null) return;
            notification.displayMessage("You have a new task",currTask.getTitle().trim());
         } catch (Exception e) {
             logger.error("An error running thread",e);
        }
    }

    public void setManager(ScheduledExecutorService scheduler) {
        this.scheduler = scheduler;
    }

    public ScheduledExecutorService getManager() {
       return this.scheduler;
    }
}
