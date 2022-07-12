package ua.edu.sumdu.j2se.kostrytsyn.tasks.controller;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.Task;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.view.View;

public class ChangeTaskController extends Controller{
    protected ChangeTaskController(View view, int action) {
        super(view, action);
    }

    protected static boolean changeTask(View view,Task currTask) {
        TaskUtil.setTitleOfTask(view, currTask);
        TaskUtil.setStartTimeOfTask(view, currTask);
        currTask.setEndTime(currTask.getStartTime());
        boolean isRepeated = currTask.isRepeated();
        if (isRepeated) {
            TaskUtil.setEndTimeOfTask(view, currTask);
            TaskUtil.setInterval(view, currTask);
        }
        return true;
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
            TaskUtil.setActiveOfTask(view, currTask);
            if(!currTask.isActive()){
                System.out.println("Task was deactivated!");
                return Controller.CHANGE_MENU_ACTION;
            }
            TaskUtil.setTypeOfTask(view, currTask);
            if (changeTask(view,currTask)) System.out.println("Task was changed!");
        }
        return Controller.CHANGE_MENU_ACTION;
    }
}
