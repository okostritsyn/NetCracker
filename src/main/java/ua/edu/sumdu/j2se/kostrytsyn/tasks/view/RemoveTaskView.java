package ua.edu.sumdu.j2se.kostrytsyn.tasks.view;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;

public class RemoveTaskView implements View{
    int maxElement;

    @Override
    public int readAction() {
        int selectedElement;

        try {
            selectedElement = Integer.parseInt(readInputString());
        } catch (NumberFormatException e) {
            System.out.println("incorrect number! Make your choice:");
            return readAction();
        }

        if (selectedElement < 0 || selectedElement > maxElement) {
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

        System.out.println("Enter number of task to remove, 0 to return:");

    }
}
