package ua.edu.sumdu.j2se.kostrytsyn.tasks.controller;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.view.*;

import java.util.ArrayList;
import java.util.List;

public class ChangeMenuController extends Controller{
    private AbstractTaskList taskList;
    private List<Controller> controllers = new ArrayList<>();

    public ChangeMenuController(AbstractTaskList taskList, View mainView){
        super(mainView,Controller.CHANGE_MENU_ACTION);
        this.taskList = taskList;
        controllers.add(this);
        controllers.add(new ChangeTaskController(new ChangeTaskView(),Controller.CHANGE_ACTION));
        controllers.add(new RemoveTaskController(new RemoveTaskView(),Controller.REMOVE_ACTION));
        controllers.add(new ViewTaskController(new ViewTaskView(),Controller.VIEW_ACTION));
    }

    @Override
    public int process(AbstractTaskList taskList) {
        view.printInfo(taskList);
        int action = view.readAction();
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
