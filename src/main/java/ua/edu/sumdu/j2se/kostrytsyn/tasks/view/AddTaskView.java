package ua.edu.sumdu.j2se.kostrytsyn.tasks.view;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.controller.Controller;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;

public class AddTaskView implements View {
    private AbstractTaskList currTaskList;

    @Override
    public int readAction() {
        int selectedElement = 0;
        try {
            selectedElement = Integer.parseInt(readInputString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (selectedElement == 3) {
            return Controller.MAIN_MENU_ACTION;
        } else if (selectedElement != 1 && selectedElement != 2) {
            printInfo(currTaskList);
            return readAction();
        }
        return selectedElement;
    }

    @Override
    public void printInfo(AbstractTaskList taskList) {
        clearScreen();
        currTaskList = taskList;

        System.out.println("Do you want add repeatable or nonrepeatable task?");
        System.out.println("1. Repeatable");
        System.out.println("2. Nonrepeatable");
        System.out.println("3. Return");
    }
}