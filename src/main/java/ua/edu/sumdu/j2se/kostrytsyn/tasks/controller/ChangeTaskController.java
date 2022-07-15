package ua.edu.sumdu.j2se.kostrytsyn.tasks.controller;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.exceptions.CheckTimeException;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.Task;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.view.View;

import java.time.LocalDateTime;

public class ChangeTaskController extends Controller{
    protected ChangeTaskController(View view, int action) {
        super(view, action);
    }

    protected static boolean changeTask(View view,Task currTask) {
        TaskDataUtil.setTitleOfTask(view, currTask);
        changeTaskTime(view,currTask);
        boolean isRepeated = currTask.isRepeated();
        if (isRepeated) {
            TaskDataUtil.setInterval(view, currTask);
        }
        BackgroundJobManager jobManager = new BackgroundJobManager();
        Controller.addRunTaskController(jobManager.init(currTask));

        return true;
    }

    private static void changeTaskTime(View view,Task currTask) {
        TaskDataUtil.setStartTimeOfTask(view, currTask);

        if (currTask.getEndTime().isEqual(LocalDateTime.MAX)){
            currTask.setEndTime(currTask.getStartTime());
        }

        boolean isRepeated = currTask.isRepeated();
        if (isRepeated) {
            TaskDataUtil.setEndTimeOfTask(view, currTask);

            try {
                checkTaskTime(currTask);
            } catch (CheckTimeException e) {
                System.out.println("Start time cannot be bigger than end time!");
                changeTaskTime(view,currTask);
            }

        } else { // on case changing unrepeatable task
            currTask.setEndTime(currTask.getStartTime());
        }
    }

    private static void checkTaskTime(Task currTask) throws CheckTimeException {
        if (currTask.getEndTime().isBefore(currTask.getStartTime())){
            throw new CheckTimeException("Start time cannot be bigger than end time!");
        }
    }

    @Override
    public int process(AbstractTaskList taskList) {
        int currAction = super.process(taskList);
        if (currAction != 0) {
            Task currTask = taskList.getTask(currAction-1);
            if (currTask == null) {
                System.out.println("There is no such task! enter correct number!");
                return process(taskList);
            }
            System.out.println("You select task: " + currTask);
            TaskDataUtil.setActiveOfTask(view, currTask);
            if(!currTask.isActive()){
                System.out.println("Task was deactivated!");
                return Controller.CHANGE_MENU_ACTION;
            }
            TaskDataUtil.setTypeOfTask(view, currTask);
            if (changeTask(view,currTask)) System.out.println("Task was changed!");
        }
        return Controller.CHANGE_MENU_ACTION;
    }
}
