package ua.edu.sumdu.j2se.kostrytsyn.tasks.controller;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.view.View;

public abstract class Controller {
    public static final int MAIN_MENU_ACTION = 0;
    public static final int ADD_TASK_ACTION = 1;
    public static final int SET_PERIOD_ACTION = 2;
    public static final int CHANGE_MENU_ACTION = 3;
    public static final int SETTINGS_ACTION = 4;
    public static final int FINISH_ACTION = 5;

    public static final int SHIFT_MENU_TASK = 5;
    public static final int CHANGE_ACTION = 6;
    public static final int REMOVE_ACTION = 7;
    public static final int VIEW_ACTION = 8;

    public static final int SHIFT_MENU_SETTINGS = 8;
    public static final int SET_CATALOG_ACTION = 9;
    public static final int SET_TYPE_ACTION = 10;

    protected View view;
    protected int action;

    protected Controller(View view, int action){
        this.view = view;
        this.action = action;
    }

    public boolean canProcess(int action){return this.action == action;}

    public int process(AbstractTaskList taskList){
        view.printInfo(taskList);
        return view.readAction();}
}
