package ua.edu.sumdu.j2se.kostrytsyn.tasks.view;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainView implements View {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public int printInfo(AbstractTaskList taskList) {
        clearScreen();
        System.out.println("Make a choice:");
        System.out.println("1. New task");
        System.out.println("2. Set period");
        System.out.println("3. Select task");
        System.out.println("4. Settings");
        System.out.println("5. Exit");
        System.out.println("List of tasks: current period - ");

        if (taskList == null){
            System.out.println("<empty>");
        }else {
            int i = 0;
            for (Task task : taskList) {
                System.out.println(i + " " + task);
                i++;
            }
        }

        int variant = 0;
        try{
            variant = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return variant;
    }
}
