package ua.edu.sumdu.j2se.kostrytsyn.tasks.view;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.controller.Controller;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;

public class SaveTasksToFileView implements View {
    @Override
    public void printInfo(AbstractTaskList taskList) {
        clearScreen();
        System.out.println("1.Set path to catalog to save");
        System.out.println("0.Return");

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
        } else if (selectedElement < 0 || selectedElement > 1) {
            System.out.println("incorrect number! Make your choice: ");
            return readAction();
        }
        return selectedElement;
    }
}
