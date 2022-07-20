package ua.edu.sumdu.j2se.kostrytsyn.tasks.view;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public interface View {
    void printInfo(AbstractTaskList taskList);
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String collectDataFromUser(String message, String currentValue);

    int readAction();

    default String readInputString() throws IOException {
        Logger logger = Logger.getLogger(View.class);
        String data;
        try  {
            data = reader.readLine();
        } catch (IOException e) {
            logger.error("Error while read buffer", e);
            throw new IOException(e);
        }
        return data.trim();
    }

    default void clearScreen() {
        final String ANSI_CLS = "\u001b[2J";
        final String ANSI_HOME = "\u001b[H";
        System.out.print(ANSI_CLS + ANSI_HOME);
        System.out.flush();
    }
}
