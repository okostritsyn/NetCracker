package ua.edu.sumdu.j2se.kostrytsyn.tasks.view;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.controller.Controller;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;

public class SetTypeView implements View {
    @Override
    public void printInfo(AbstractTaskList taskList) {
        System.out.println("type was set");
    }

    @Override
    public int readAction() {
        return Controller.SETTINGS_ACTION;
    }
}
