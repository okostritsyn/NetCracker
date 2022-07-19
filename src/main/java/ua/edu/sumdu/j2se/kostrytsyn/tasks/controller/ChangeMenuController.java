package ua.edu.sumdu.j2se.kostrytsyn.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.exceptions.EmptyListOfControllersException;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.view.*;

import java.util.ArrayList;
import java.util.List;

public class ChangeMenuController extends Controller{
    private final List<Controller> controllers = new ArrayList<>(5);

    public ChangeMenuController(View mainView){
        super(mainView,Controller.CHANGE_MENU_ACTION);
        controllers.add(this);
        controllers.add(new AddTaskController(new AddTaskView(),Controller.ADD_TASK_ACTION));
        controllers.add(new ChangeTaskController(new ChangeTaskView(),Controller.CHANGE_ACTION));
        controllers.add(new RemoveTaskController(new RemoveTaskView(),Controller.REMOVE_ACTION));
        controllers.add(new SetPeriodController(new SetPeriodView(),Controller.SET_PERIOD_ACTION));
    }

    @Override
    public boolean canProcess(int action){return this.action == action;}

    @Override
    public int process(AbstractTaskList taskList) {
        int action = super.process(taskList);
        final Logger logger = Logger.getLogger(ChangeMenuController.class.getName());

        if (action != 0) {
            try {
                return processMenu(action,Controller.CHANGE_MENU_ACTION,controllers);
            } catch (EmptyListOfControllersException e) {
                logger.error("There is no controllers to process! ",e);
            }
        }
        return Controller.MAIN_MENU_ACTION;
    }
}
