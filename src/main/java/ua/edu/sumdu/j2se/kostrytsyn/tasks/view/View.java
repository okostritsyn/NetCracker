package ua.edu.sumdu.j2se.kostrytsyn.tasks.view;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public interface View {
    void printInfo(AbstractTaskList taskList);

    int readAction();

    default String readInputString() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String data = "";
        try {
            data = reader.readLine().trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data.trim();
    }

    default String collectDataFromUser(String message){
        System.out.println(message);
        return readInputString();
    }

    default void clearScreen(){
        final String ANSI_CLS = "\u001b[2J";
        final String ANSI_HOME = "\u001b[H";
        System.out.print(ANSI_CLS + ANSI_HOME);
        System.out.flush();
    }
}
