package ua.edu.sumdu.j2se.kostrytsyn.tasks.view;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.controller.Controller;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;

public class ChangeTaskView implements View {
    @Override
    public int readAction() {
        return Controller.CHANGE_MENU_ACTION;
    }

    @Override
    public void printInfo(AbstractTaskList taskList) {
        System.out.println("Task was change");
    }
}
