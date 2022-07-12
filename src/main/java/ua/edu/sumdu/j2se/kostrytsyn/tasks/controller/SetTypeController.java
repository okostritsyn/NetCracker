package ua.edu.sumdu.j2se.kostrytsyn.tasks.controller;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.ListTypes;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.Task;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.TaskListFactory;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.view.View;

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
                return Controller.CHANGE_MENU_ACTION;
            }
            Controller.setCurrentTypeList(newTypeList);
            taskList = copyTasksToNewTaskList(taskList,newTypeList);
        }
        return Controller.CHANGE_MENU_ACTION;
    }

    private AbstractTaskList copyTasksToNewTaskList(AbstractTaskList currList, ListTypes newTypeList) {
        AbstractTaskList taskList = TaskListFactory.createTaskList(newTypeList);

        for (Task task: currList) {
            taskList.add(task);
        }

        return taskList;
    }
}
