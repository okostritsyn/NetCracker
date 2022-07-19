package ua.edu.sumdu.j2se.kostrytsyn.tasks.view;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;

public class RemoveTaskView extends AbstractView {
    int maxElement;

    @Override
    public int readAction() {
        int selectedElement = super.readAction();

        while(!checkAction(selectedElement,0,maxElement)){
            System.out.println("incorrect number! Make your choice: ");
            selectedElement = super.readAction();
        }

        return selectedElement;
    }

    @Override
    public void printInfo(AbstractTaskList taskList) {
        clearScreen();
        maxElement = taskList.size();
        printListOfTasks(taskList);

        System.out.println("Enter number of task to remove, 0 to return:");

    }
}
