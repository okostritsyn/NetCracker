package ua.edu.sumdu.j2se.kostrytsyn.tasks.controller;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.view.*;

import java.util.ArrayList;
import java.util.List;

public class MainController extends Controller {
    private final AbstractTaskList taskList;
    private final List<Controller> controllers = new ArrayList<>();

    public MainController(AbstractTaskList taskList, View mainView){
        super(mainView,Controller.MAIN_MENU_ACTION);
        this.taskList = taskList;
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
                    action = controller.process(this.taskList);
                }
            }
        } while (action != FINISH_ACTION);
        return FINISH_ACTION;
    }
}
