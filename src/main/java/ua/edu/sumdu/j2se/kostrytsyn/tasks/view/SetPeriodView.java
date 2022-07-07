package ua.edu.sumdu.j2se.kostrytsyn.tasks.view;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.controller.Controller;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;

public class SetPeriodView implements View {
    @Override
    public void printInfo(AbstractTaskList taskList) {
        System.out.println("Set period");
    }

    @Override
    public int readAction() {
        return Controller.MAIN_MENU_ACTION;
    }
}
