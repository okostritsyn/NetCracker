package ua.edu.sumdu.j2se.kostrytsyn.tasks.view;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.controller.Controller;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;

public class ChangeMenuView extends AbstractView {
    @Override
    public int readAction() {
        int selectedElement = super.readAction();

        while(!checkAction(selectedElement,0,4)){
            System.out.println("incorrect number! Make your choice: ");
            selectedElement = super.readAction();
        }

        if (selectedElement==0) {
            return Controller.MAIN_MENU_ACTION;
        }

        return selectedElement  + Controller.SHIFT_MENU_TASK;
    }

    @Override
    public void printInfo(AbstractTaskList taskList) {
        clearScreen();

        printListOfTasks(taskList);

        System.out.println(" ");
        System.out.println("Make a choice:");
        System.out.println("1. Add new task");
        System.out.println("2. Change task");
        System.out.println("3. Remove task");
        System.out.println("4. Set period");
        System.out.println("0. Return");
    }
}
