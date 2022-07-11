package ua.edu.sumdu.j2se.kostrytsyn.tasks.view;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.controller.Controller;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.Task;

public class MainView implements View {
    private AbstractTaskList currTaskList;

    @Override
    public int readAction() {
        int variant = 0;
        try{
            variant = Integer.parseInt(readInputString());
        } catch (NumberFormatException e) {
            System.out.println("incorrect number!");
            return readAction();
        }
        if (variant==5) {
            return Controller.FINISH_ACTION;
        }else if(variant < 1 || variant > 5){
            System.out.println("incorrect number!");
            return readAction();
        }
        return variant;
    }

    @Override
    public void printInfo(AbstractTaskList taskList) {
        clearScreen();
        currTaskList = taskList;
        System.out.println("Make a choice:");
        System.out.println("1. New task");
        System.out.println("2. Set period");
        System.out.println("3. Select task");
        System.out.println("4. Settings");
        System.out.println("5. Exit");
        System.out.println("List of tasks: current period - ");

        if (taskList == null || taskList.size() == 0 ) {
            System.out.println("<List of tasks is empty>");
        }else {
            int i = 1;
            for (Task task : taskList) {
                System.out.println(i + ". " + task);
                i++;
            }
        }
    }
}
