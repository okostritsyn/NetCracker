package ua.edu.sumdu.j2se.kostrytsyn.tasks.controller;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.Task;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.view.View;

import java.time.LocalDateTime;

public class AddTaskController extends Controller{
    protected AddTaskController(View view, int action) {
        super(view, action);
    }

    private Task getNewTask(int typeOfTask) {
        Task currTask = new Task("", LocalDateTime.now());
        currTask.setRepeated(typeOfTask == 1);
        currTask.setActive(true);
        ChangeTaskController.changeTask(view,currTask);
        return currTask;
    }

    @Override
    public int process(AbstractTaskList taskList) {
        int currAction = super.process(taskList);
        if (currAction != 0) {
            Task newTask = getNewTask(currAction);
            taskList.add(newTask);
            System.out.println("Task was added!");
        }
        return Controller.CHANGE_MENU_ACTION;
    }
}
