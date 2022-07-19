package ua.edu.sumdu.j2se.kostrytsyn.tasks.view;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.controller.Controller;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;

public class MainView extends AbstractView {

    @Override
    public int readAction() {
        int selectedElement = super.readAction();

        while(!checkAction(selectedElement,0,2)){
            System.out.println("incorrect number! Make your choice: ");
            selectedElement = super.readAction();
        }

        if (selectedElement==0) {
            return Controller.FINISH_ACTION;
        }
        return selectedElement;
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
