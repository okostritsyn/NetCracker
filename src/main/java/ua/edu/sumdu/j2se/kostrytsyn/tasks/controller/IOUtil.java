package ua.edu.sumdu.j2se.kostrytsyn.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.ListTypes;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.TaskIO;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IOUtil {
    final static Logger logger = Logger.getLogger(IOUtil.class);

    public static String getPostFixOfFile() {
        return Controller.getCurrentTypeList().equals(ListTypes.LINKED) ? "linked" : "array";
    }

    public static void readTasksFromCatalog(AbstractTaskList taskList, Path path) {
        File newFile = new File(path.toString(), "tasks_" + getPostFixOfFile() + ".json");
        boolean fileExist = newFile.exists();
        if (!fileExist) {
            return;
        }
        TaskIO.readText(taskList, newFile);
    }

    public static void writeTasksToCatalog(AbstractTaskList taskList, Path path) {
        File newFile = new File(path.toString(), "tasks_" + getPostFixOfFile() + ".json");
        boolean fileExist;
        try {
            fileExist = newFile.exists();
            if (!fileExist) {
                fileExist = newFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file " + newFile.getAbsolutePath());
            System.out.println(e.getMessage());
            logger.error("Error writing to file " + newFile.getAbsolutePath(), e);
            return;
        }
        if (!fileExist) {
            System.out.println("Error writing to file " + newFile.getAbsolutePath());
            return;
        }
        TaskIO.writeText(taskList, newFile);
    }

    public static void deleteFileOfTasks(Path path) {
        File newFile = new File(path.toString(), "tasks_" + getPostFixOfFile() + ".json");
        boolean isDeleted = true;
        if (newFile.exists()) {
            isDeleted = newFile.delete();
        }
        if (!isDeleted) {
            System.out.println("Error deleting file of settings " + newFile.getAbsolutePath());
        }
    }

    public static void saveTasksToFile(AbstractTaskList taskList) {
        Path path = Paths.get(Controller.getCurrentCatalog());
        if (Files.exists(path, LinkOption.NOFOLLOW_LINKS) && Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
            IOUtil.writeTasksToCatalog(taskList, path);
        } else {
            System.out.println("!!! Catalog to save tasks does not exist!");
        }
    }

}
