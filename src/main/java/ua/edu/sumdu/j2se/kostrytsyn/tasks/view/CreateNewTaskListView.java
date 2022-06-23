package ua.edu.sumdu.j2se.kostrytsyn.tasks.view;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.controller.Controller;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;

public class CreateNewTaskListView implements View {
    @Override
    public int printInfo(AbstractTaskList taskList) {
        System.out.println("New task list was created");
        return Controller.MAIN_MENU_ACTION;
    }

}
