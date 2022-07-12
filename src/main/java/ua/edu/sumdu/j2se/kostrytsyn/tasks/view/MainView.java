package ua.edu.sumdu.j2se.kostrytsyn.tasks.view;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.controller.Controller;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;

public class MainView implements View {

    @Override
    public int readAction() {
        int variant;
        try{
            variant = Integer.parseInt(readInputString());
        } catch (NumberFormatException e) {
            System.out.println("incorrect number!");
            return readAction();
        }
        if (variant==0) {
            return Controller.FINISH_ACTION;
        }else if(variant < 0 || variant > 2){
            System.out.println("incorrect number!");
            return readAction();
        }
        return variant;
    }

    @Override
    public void printInfo(AbstractTaskList taskList) {
        clearScreen();

        System.out.println("Make a choice:");
        System.out.println("1. View tasks");
        System.out.println("2. Settings");
        System.out.println("0. Exit");
    }
}
