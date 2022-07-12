package ua.edu.sumdu.j2se.kostrytsyn.tasks.view;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.controller.Controller;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;

public class SetTypeView implements View {
    @Override
    public void printInfo(AbstractTaskList taskList) {
        clearScreen();

        System.out.println("Current type is "+Controller.getCurrentTypeList());

        System.out.println("Select type of list");
        System.out.println("1. Array");
        System.out.println("2. Linked");
        System.out.println("0. Return");
    }

    @Override
    public int readAction() {
        int selectedElement;
        try {
            selectedElement = Integer.parseInt(readInputString());
        } catch (NumberFormatException e) {
            System.out.println("incorrect number! Make your choice:");
            return readAction();
        }
        if (selectedElement==0) {
            return Controller.SETTINGS_ACTION;
        } else if (selectedElement < 0 || selectedElement > 2) {
            System.out.println("incorrect number! Make your choice: ");
            return readAction();
        }
        return selectedElement;
    }
}
