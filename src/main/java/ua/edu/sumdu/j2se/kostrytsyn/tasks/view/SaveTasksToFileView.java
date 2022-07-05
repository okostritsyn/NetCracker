package ua.edu.sumdu.j2se.kostrytsyn.tasks.view;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.controller.Controller;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;

public class SaveTasksToFileView implements View {
    @Override
    public int printInfo(AbstractTaskList taskList) {
        System.out.println("set catalog");
        return Controller.SETTINGS_ACTION;
    }

}
