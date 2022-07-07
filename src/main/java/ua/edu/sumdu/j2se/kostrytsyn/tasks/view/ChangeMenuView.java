package ua.edu.sumdu.j2se.kostrytsyn.tasks.view;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.controller.Controller;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;

public class ChangeMenuView implements View {
    private AbstractTaskList currTaskList;

    @Override
    public int readAction() {
        int variant = 0;
        try{
            variant = Integer.parseInt(readInputString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (variant==4) {
            return Controller.MAIN_MENU_ACTION;
        }else if(variant < 1 || variant > 4){
            printInfo(currTaskList);
            return readAction();
        }
        return variant  + Controller.SHIFT_MENU_TASK;
    }

    @Override
    public void printInfo(AbstractTaskList taskList) {
        clearScreen();
        currTaskList = taskList;

        System.out.println("Make a choice:");
        System.out.println("1. Change task");
        System.out.println("2. Remove task");
        System.out.println("3. View task");
        System.out.println("4. Return");

    }
}
