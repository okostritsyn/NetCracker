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
        controllers.add(new ChangeMenuController(taskList,new ChangeMenuView()));
        controllers.add(new SettingsMenuController(taskList,new SettingsMenuView()));
    }

    @Override
    public int process(AbstractTaskList taskList) {
        int action = super.process(taskList);

        if (controllers.size() == 0){
            action = FINISH_ACTION;
        }
        do {
            for (Controller controller : controllers) {
                if (controller.canProcess(action)) {
                    action = controller.process(Controller.getTaskList());
                }
            }
        } while (action != FINISH_ACTION);

        IOUtil.saveTasksToFile(taskList);

        for (RunTaskController currScheduler:Controller.RunTaskControllers()) {
            if (currScheduler == null) continue;
            currScheduler.getManager().shutdownNow();
        }

        return FINISH_ACTION;
    }
}
