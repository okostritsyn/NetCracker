package ua.edu.sumdu.j2se.kostrytsyn.tasks.view;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;

public class ChangeTaskView extends AbstractView {
    int maxElement;
    @Override
    public int readAction() {
        int selectedElement = super.readAction();

        if (!checkAction(selectedElement,0,maxElement)){
            System.out.println("incorrect number! Make your choice: ");
            return readAction();
        }

       return selectedElement;
    }

    @Override
    public void printInfo(AbstractTaskList taskList) {
        clearScreen();
        maxElement = taskList.size();
        printListOfTasks(taskList);

        System.out.println("Enter number of task to change, 0 to return:");

    }
}


