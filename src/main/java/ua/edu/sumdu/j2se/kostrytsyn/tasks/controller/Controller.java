package ua.edu.sumdu.j2se.kostrytsyn.tasks.controller;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.view.View;

public abstract class Controller {
    public static final int MAIN_MENU_ACTION = 0;
    public static final int EMPTY_LIST_ACTION = 1;
    public static final int ADD_TASK_ACTION = 2;
    public static final int REMOVE_TASK_ACTION = 3;
    public static final int CALENDAR_ACTION = 4;
    public static final int FINISH_ACTION = 5;

    protected View view;
    protected int action;

    public Controller(View view, int action){
        this.view = view;
        this.action = action;
    }

    public boolean canProcess(int action){return this.action == action;}

    public int process(AbstractTaskList taskList){return view.printInfo(taskList);}
}
