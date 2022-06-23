package ua.edu.sumdu.j2se.kostrytsyn.tasks.view;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainView implements View {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public int printInfo(AbstractTaskList taskList) {
        System.out.println("Make a choice:");
        System.out.println("1. Check tasks");
        System.out.println("2. Add task");
        System.out.println("3. Remove task");
        System.out.println("4. Show calendar of tasks");
        System.out.println("5. Exit");

        int variant = 0;
        try{
            variant = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return variant;
    }
}
