package ua.edu.sumdu.j2se.kostrytsyn.tasks.controller;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.view.SaveTasksToFileView;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.view.SetTypeView;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.view.View;

import java.util.ArrayList;
import java.util.List;

public class SettingsMenuController extends Controller{
    private final List<Controller> controllers = new ArrayList<>();

    public SettingsMenuController( View mainView){
        super(mainView,Controller.SETTINGS_ACTION);
        controllers.add(this);
        controllers.add(new SaveTasksToFileController(new SaveTasksToFileView(),Controller.SET_CATALOG_ACTION));
        controllers.add(new SetTypeController(new SetTypeView(),Controller.SET_TYPE_ACTION));
    }

    @Override
    public int process(AbstractTaskList taskList) {
        int action = super.process(taskList);
        if (action != 0) {
            return processMenu(action,Controller.SETTINGS_ACTION,controllers,SettingsMenuController.class.getName());
        }
        return Controller.MAIN_MENU_ACTION;
    }
}
