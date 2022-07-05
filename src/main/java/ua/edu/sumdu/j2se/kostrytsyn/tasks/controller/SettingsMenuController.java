package ua.edu.sumdu.j2se.kostrytsyn.tasks.controller;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.view.SaveTasksToFileView;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.view.SetTypeView;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.view.View;

import java.util.ArrayList;
import java.util.List;

public class SettingsMenuController extends Controller{
    private AbstractTaskList taskList;
    private List<Controller> controllers = new ArrayList<>();

    public SettingsMenuController(AbstractTaskList taskList, View mainView){
        super(mainView,Controller.SETTINGS_ACTION);
        this.taskList = taskList;
        controllers.add(this);
        controllers.add(new SaveTasksToFileController(new SaveTasksToFileView(),Controller.SET_CATALOG_ACTION));
        controllers.add(new SetTypeController(new SetTypeView(),Controller.SET_TYPE_ACTION));
    }

    @Override
    public int process(AbstractTaskList taskList) {
        int action = view.printInfo(taskList);
        if (controllers.size() == 0){
            action = MAIN_MENU_ACTION;
        }
        do {
            for (Controller controller : controllers) {
                if (controller.canProcess(action)) {
                    action = controller.process(this.taskList);
                }
            }
        } while (action != MAIN_MENU_ACTION);
        return MAIN_MENU_ACTION;
    }
}
