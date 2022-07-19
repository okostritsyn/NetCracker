package ua.edu.sumdu.j2se.kostrytsyn.tasks.view;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.controller.Controller;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;

public class SettingsMenuView extends AbstractView {
    @Override
    public void printInfo(AbstractTaskList taskList) {
        clearScreen();
        System.out.println("Make a choice:");
        System.out.println("1. Save tasks to file");
        System.out.println("2. Set type of list");
        System.out.println("0. Return");
    }

    @Override
    public int readAction() {
        int selectedElement = super.readAction();

        while(!checkAction(selectedElement,0,2)){
            System.out.println("incorrect number! Make your choice: ");
            selectedElement = super.readAction();
        }

        if (selectedElement==0) {
            return Controller.MAIN_MENU_ACTION;
        }

        return selectedElement  + Controller.SHIFT_MENU_SETTINGS;
    }
}
