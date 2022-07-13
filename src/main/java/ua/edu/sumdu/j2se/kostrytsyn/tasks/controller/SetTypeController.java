package ua.edu.sumdu.j2se.kostrytsyn.tasks.controller;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.ListTypes;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.Task;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.TaskListFactory;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.view.View;

import java.nio.file.Paths;

public class SetTypeController extends Controller{
    protected SetTypeController(View view, int action) {
        super(view, action);
    }

    @Override
    public int process(AbstractTaskList taskList) {
        int currAction = super.process(taskList);
        if (currAction != 0) {
            ListTypes newTypeList = currAction == 1? ListTypes.ARRAY:ListTypes.LINKED;

            if (Controller.getCurrentTypeList().equals(newTypeList)) {
                return Controller.MAIN_MENU_ACTION;
            }

            TaskUtil.deleteFileOfTasks(Paths.get(Controller.getCurrentCatalog()));

            Controller.setCurrentTypeList(newTypeList);

            AbstractTaskList newTaskList = makeCopyTasksToNewTaskList(taskList,newTypeList);

            Controller.setTaskList(newTaskList);

            TaskUtil.saveTasksToFile(newTaskList);

            System.out.println("Type of task list was changed to "+Controller.getCurrentTypeList());
        }
        return Controller.MAIN_MENU_ACTION;
    }

    private AbstractTaskList makeCopyTasksToNewTaskList(AbstractTaskList currList, ListTypes newTypeList) {
        AbstractTaskList newTaskList = TaskListFactory.createTaskList(newTypeList);

        for (Task task: currList) {
            newTaskList.add(task);
        }
        return newTaskList;
    }
}
