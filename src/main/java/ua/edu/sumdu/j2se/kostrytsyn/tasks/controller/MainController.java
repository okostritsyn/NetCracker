package ua.edu.sumdu.j2se.kostrytsyn.tasks.controller;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.view.ChangeMenuView;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.view.SettingsMenuView;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainController extends Controller {
    private final List<Controller> controllers = new ArrayList<>();

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

        action = processMenu(action,Controller.FINISH_ACTION,controllers,MainController.class.getName());

        IOUtil.saveTasksToFile(taskList);

        for (RunTaskController currScheduler:Controller.RunTaskControllers()) {
            if (currScheduler == null) continue;
            currScheduler.getManager().shutdownNow();
        }

        return action;
    }
}
