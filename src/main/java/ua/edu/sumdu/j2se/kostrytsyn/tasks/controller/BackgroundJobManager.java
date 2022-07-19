package ua.edu.sumdu.j2se.kostrytsyn.tasks.controller;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.Task;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BackgroundJobManager {

    private ScheduledExecutorService scheduler;

    public RunTaskController init(Task currTask) {
        if ((LocalDateTime.now().isAfter(currTask.getEndTime())) || !currTask.isActive()) {
            return null;
        }

        scheduler = Executors.newSingleThreadScheduledExecutor();

        RunTaskController runTaskController = new RunTaskController();
        runTaskController.setCurrTask(currTask);
        runTaskController.setManager(scheduler);

        if (currTask.isRepeated()){
            scheduler.scheduleAtFixedRate(runTaskController, 0,currTask.getRepeatInterval(), TimeUnit.HOURS);
        } else{
            scheduler.schedule(runTaskController, 3, TimeUnit.SECONDS);
        }
        return runTaskController;
    }
}
