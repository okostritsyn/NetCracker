package ua.edu.sumdu.j2se.kostrytsyn.tasks.view;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.controller.Controller;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SettingsMenuView implements View {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public int printInfo(AbstractTaskList taskList) {
        clearScreen();

        System.out.println("Make a choice:");
        System.out.println("1. Change catalog");
        System.out.println("2. Set type of list");
        System.out.println("3. Return");

        int variant = 0;
        try{
            variant = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return variant==3 ? Controller.MAIN_MENU_ACTION : variant+Controller.SHIFT_MENU_SETTINGS;
    }
}
