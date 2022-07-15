package ua.edu.sumdu.j2se.kostrytsyn.tasks.view;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;

public class AddTaskView extends AbstractView {
    @Override
    public int readAction() {
        int selectedElement = super.readAction();

        if (!checkAction(selectedElement, 0, 2)) {
            System.out.println("incorrect number! Make your choice: ");
            return readAction();
        }
        return selectedElement;
    }

    @Override
    public void printInfo(AbstractTaskList taskList) {
        clearScreen();

        System.out.println("Do you want add repeatable or unrepeatable task?");
        System.out.println("1. Repeatable");
        System.out.println("2. Unrepeatable");
        System.out.println("0. Return");
    }
}