package ua.edu.sumdu.j2se.kostrytsyn.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.exceptions.EmptyListOfControllersException;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.view.SaveTasksToFileView;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.view.SetTypeView;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.view.View;

import java.util.ArrayList;
import java.util.List;

public class SettingsMenuController extends Controller{
    private final List<Controller> controllers = new ArrayList<>(3);

    public SettingsMenuController( View mainView){
        super(mainView,Controller.SETTINGS_ACTION);
        controllers.add(this);
        controllers.add(new SaveTasksToFileController(new SaveTasksToFileView(),Controller.SET_CATALOG_ACTION));
        controllers.add(new SetTypeController(new SetTypeView(),Controller.SET_TYPE_ACTION));
    }

    @Override
    public int process(AbstractTaskList taskList) {
        int action = super.process(taskList);
        final Logger logger = Logger.getLogger(SettingsMenuController.class.getName());

        if (action != 0) {
            try {
                return processMenu(action,Controller.SETTINGS_ACTION,controllers);
            } catch (EmptyListOfControllersException e) {
                logger.error("There is no controllers to process! ",e);
            }
        }
        return Controller.MAIN_MENU_ACTION;
    }
}
