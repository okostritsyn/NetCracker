package ua.edu.sumdu.j2se.kostrytsyn.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.exceptions.EmptyListOfControllersException;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.view.ChangeMenuView;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.view.SettingsMenuView;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainController extends Controller {
    private final List<Controller> controllers = new ArrayList<>(3);

    public MainController(AbstractTaskList taskList, View mainView){
        super(mainView,Controller.MAIN_MENU_ACTION);
        Controller.setTaskList(taskList);
        controllers.add(this);
        controllers.add(new ChangeMenuController(new ChangeMenuView()));
        controllers.add(new SettingsMenuController(new SettingsMenuView()));
    }

    @Override
    public int process(AbstractTaskList taskList) {
        int action = super.process(taskList);
        final Logger logger = Logger.getLogger(MainController.class.getName());
        int finishAction = Controller.FINISH_ACTION;

        try {
            return processMenu(action,finishAction,controllers);
        } catch (EmptyListOfControllersException e) {
            logger.error("There is no controllers to process! ",e);
            action = finishAction;
        }

        return action;
    }
}
