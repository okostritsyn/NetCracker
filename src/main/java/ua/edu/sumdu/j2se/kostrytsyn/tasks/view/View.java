package ua.edu.sumdu.j2se.kostrytsyn.tasks.view;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.controller.Controller;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public interface View {
    void printInfo(AbstractTaskList taskList);

    int readAction();

    default void printListOfTasks(AbstractTaskList taskList){
        System.out.println("List of tasks: current period - "+ Controller.getCurrentPeriodStr());

        if (taskList == null || taskList.size() == 0 ) {
            System.out.println("<List of tasks is empty>");
        }else {
            System.out.printf("%-2s %-30s %-1s %-20s %-20s %-2s", "№", "Title", "A", "Start date", "End date", "Interval");
            System.out.println(" ");
            int i = 1;
            for (Task task : taskList) {

                if (task.getStartTime().toLocalDate().isAfter(Controller.getPeriodEnd()) ||
                        task.getEndTime().toLocalDate().isBefore(Controller.getPeriodStart())) {
                    continue;
                }
                System.out.printf("%-2s %-30s %-1s %-20s %-20s %-2s", Integer.toString(i), task.getTitle().trim(), task.isActive()?"\u2713":" ", task.getStartTimeStr(),task.getEndTimeStr(),Integer.toString(task.getRepeatInterval()));
                System.out.println(" ");
                i++;
            }
        }
    }

    default String readInputString() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String data = "";
        try {
            data = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data.trim();
    }

    default String collectDataFromUser(String message, String currentValue){
        System.out.println(message);
        //if (!currentValue.isEmpty()) System.out.append(currentValue);
        return readInputString();
    }

    default void clearScreen(){
        final String ANSI_CLS = "\u001b[2J";
        final String ANSI_HOME = "\u001b[H";
        System.out.print(ANSI_CLS + ANSI_HOME);
        System.out.flush();
    }
}
