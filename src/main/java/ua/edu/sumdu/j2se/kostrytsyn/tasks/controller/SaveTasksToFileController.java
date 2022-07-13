package ua.edu.sumdu.j2se.kostrytsyn.tasks.controller;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.view.View;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SaveTasksToFileController extends Controller{
    protected SaveTasksToFileController(View view, int action) {
        super(view, action);
    }

    private String getCatalogToSave(View view){
        return view.collectDataFromUser("Set catalog to save",Controller.getCurrentCatalog());
    }

    @Override
    public int process(AbstractTaskList taskList) {
        int currAction = super.process(taskList);
        if (currAction != 0) {
            Path path;
            while (true){
                path = Paths.get(getCatalogToSave(view));
                if (Files.exists(path, LinkOption.NOFOLLOW_LINKS) && Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
                    break;
                } else {
                System.out.println("Catalog does not exist!");
                }
            }
            TaskUtil.writeTasksToCatalog(taskList,path);
            System.out.println("File saved to catalog!");
        }
        return Controller.MAIN_MENU_ACTION;
    }
}
