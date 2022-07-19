package ua.edu.sumdu.j2se.kostrytsyn.tasks.view;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.controller.Controller;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;

public class SetPeriodView extends AbstractView {
    @Override
    public void printInfo(AbstractTaskList taskList) {
        clearScreen();

        System.out.println("Current period is "+Controller.getCurrentPeriodStr());
        System.out.println("1.Change period");
        System.out.println("2.Reset period");
        System.out.println("0.Return");

    }

    @Override
    public int readAction() {
        int selectedElement = super.readAction();

        while(!checkAction(selectedElement,0,2)){
            System.out.println("incorrect number! Make your choice: ");
            selectedElement = super.readAction();
        }

        return selectedElement;
    }
}
