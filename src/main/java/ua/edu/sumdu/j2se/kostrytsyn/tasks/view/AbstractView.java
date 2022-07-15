package ua.edu.sumdu.j2se.kostrytsyn.tasks.view;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.controller.Controller;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.Task;

public abstract class AbstractView implements View {
    @Override
    public int readAction() {
        int selectedElement;
        try {
            selectedElement = Integer.parseInt(readInputString());
        } catch (NumberFormatException e) {
            System.out.println("incorrect number! Make your choice:");
            return readAction();
        }
        return selectedElement;
    }

    public boolean checkAction(int selectedElement, int minElem, int maxElem) {
        return selectedElement >= minElem && selectedElement <= maxElem;
    }

    public void printListOfTasks(AbstractTaskList taskList) {
        System.out.println("List of tasks: current period - " + Controller.getCurrentPeriodStr());
        System.out.printf("%-2s %-30s %-1s %-20s %-20s %-2s", "№", "Title", "A", "Start date", "End date", "Interval");
        System.out.println(" ");

        int i = 0;
        for (Task task : taskList) {

            if (task.getStartTime().toLocalDate().isAfter(Controller.getPeriodEnd()) ||
                    task.getEndTime().toLocalDate().isBefore(Controller.getPeriodStart())) {
                continue;
            }
            i++;
            System.out.printf("%-2s %-30s %-1s %-20s %-20s %-2s", i, task.getTitle().trim(), task.isActive() ? "\u2713" : " ", task.getStartTimeStr(), task.getEndTimeStr(), task.getRepeatInterval());
            System.out.println(" ");
        }

        if (i == 0) {
            System.out.println("<List of tasks is empty>");
        }
    }

    @Override
    public String collectDataFromUser(String message, String currentValue) {
        System.out.println(message);
        if (!currentValue.isEmpty()) System.out.println("Current value: " + currentValue);
        String currString = readInputString();
        return currString.isEmpty() ? currentValue : currString;
    }

    @Override
    abstract public void printInfo(AbstractTaskList taskList);

}
