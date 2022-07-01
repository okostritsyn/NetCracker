package ua.edu.sumdu.j2se.kostrytsyn.tasks.view;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.controller.Controller;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChangeMenuView implements View {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public int printInfo(AbstractTaskList taskList) {
        clearScreen();

        System.out.println("Make a choice:");
        System.out.println("1. Change task");
        System.out.println("2. Remove task");
        System.out.println("3. View task");
        System.out.println("4. Return");

        int variant = 0;
        try{
            variant = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return variant==4 ? Controller.MAIN_MENU_ACTION : variant  + Controller.SHIFT_MENU_TASK;
    }
}
