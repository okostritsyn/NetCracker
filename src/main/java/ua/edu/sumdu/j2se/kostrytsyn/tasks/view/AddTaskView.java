package ua.edu.sumdu.j2se.kostrytsyn.tasks.view;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.controller.Controller;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddTaskView implements View {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public int printInfo(AbstractTaskList taskList) {
        clearScreen();

        System.out.println("Do you want add repeatable or nonrepeatable task?");
        System.out.println("1. Repeatable");
        System.out.println("2. Nonrepeatable");
        System.out.println("3. Return");
        int typeOfTask = 0;
        try{
            typeOfTask = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (typeOfTask==3) {
            return Controller.MAIN_MENU_ACTION;
        }else if(typeOfTask != 1 || typeOfTask != 2){
            return printInfo(taskList);
        }

        System.out.println("Enter title of new task:");
        String title = "";
        try{
            title = reader.readLine().trim();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (typeOfTask == 1){
            System.out.println("Enter start time of the new task (dd-mm-yyyy hh:mm):");
            String strStartTime = "";
            try{
                strStartTime = reader.readLine().trim();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Enter end time of the new task (dd-mm-yyyy hh:mm):");
            String strEndTime = "";
            try{
                strEndTime = reader.readLine().trim();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("Enter time of the new task (dd-mm-yyyy hh:mm):");
            String strStartTime = "";
            try{
                strStartTime = reader.readLine().trim();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Task was add");
        return Controller.MAIN_MENU_ACTION;
    }
}
