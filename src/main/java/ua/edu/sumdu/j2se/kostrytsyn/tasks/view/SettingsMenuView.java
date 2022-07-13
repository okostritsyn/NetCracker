package ua.edu.sumdu.j2se.kostrytsyn.tasks.view;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.controller.Controller;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;

public class SettingsMenuView implements View {
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
        int variant = 0;
        try{
            variant = Integer.parseInt(readInputString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return variant==0 ? Controller.MAIN_MENU_ACTION : variant+Controller.SHIFT_MENU_SETTINGS;
    }
}
