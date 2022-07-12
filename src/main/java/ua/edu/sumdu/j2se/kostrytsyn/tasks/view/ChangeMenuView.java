package ua.edu.sumdu.j2se.kostrytsyn.tasks.view;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.controller.Controller;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;

public class ChangeMenuView implements View {
    @Override
    public int readAction() {
        int variant;
        try{
            variant = Integer.parseInt(readInputString());
        } catch (NumberFormatException e) {
            System.out.println("incorrect number! Make a choice:");
            return readAction();
        }
        if (variant==0) {
            return Controller.MAIN_MENU_ACTION;
        }else if(variant < 0 || variant > 4) {
            System.out.println("incorrect number! Make a choice:");
            return readAction();
        }
        return variant  + Controller.SHIFT_MENU_TASK;
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
