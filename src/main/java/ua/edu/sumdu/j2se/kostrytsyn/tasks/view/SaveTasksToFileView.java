package ua.edu.sumdu.j2se.kostrytsyn.tasks.view;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;

public class SaveTasksToFileView extends AbstractView {
    @Override
    public void printInfo(AbstractTaskList taskList) {
        clearScreen();
        System.out.println("1.Set path to catalog to save");
        System.out.println("0.Return");

    }

    @Override
    public int readAction() {
        int selectedElement = super.readAction();

        if (!checkAction(selectedElement,0,1)){
            System.out.println("incorrect number! Make your choice: ");
            return readAction();
        }

        return selectedElement;
    }
}
