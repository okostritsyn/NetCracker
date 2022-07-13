package ua.edu.sumdu.j2se.kostrytsyn.tasks.controller;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.Notification;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.Task;

import java.time.ZoneId;
import java.util.Date;
import java.util.TimerTask;

public class RunTaskController extends TimerTask {
    private Task currTask;

    public void setCurrTask(Task task) {
        this.currTask = task;
    }

    @Override
    public void run() {
        RunTaskController currTimer =  Controller.getTimerForTask(currTask);

        if (currTimer == null) return;

        Long nextTime=  currTimer.scheduledExecutionTime();
        Long currTimeEnd = Date.from(currTask.getStartTime().atZone(ZoneId.systemDefault()).toInstant()).getTime();

        if (nextTime > currTimeEnd) {
            TaskUtil.deleteSchedulerForTask(currTask);
            return;
        }

        try {
             Notification currNotification = Controller.getNotificationTray();
             currNotification.displayMessageInTray("You have a new task",currTask.getTitle().trim(),"Task manager");
         } catch (Exception e) {
             System.out.println("An error running thread " + e.getMessage());
         }
    }

    public Task getCurrTask() {
        return this.currTask;
    }
}
