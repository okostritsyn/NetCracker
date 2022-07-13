package ua.edu.sumdu.j2se.kostrytsyn.tasks.controller;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.ListTypes;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.TaskListFactory;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.view.View;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Controller {
    public static final int MAIN_MENU_ACTION = 0;
    public static final int CHANGE_MENU_ACTION = 1;
    public static final int SETTINGS_ACTION = 2;
    public static final int FINISH_ACTION = 3;

    public static final int SHIFT_MENU_TASK = 3;
    public static final int ADD_TASK_ACTION = 4;
    public static final int CHANGE_ACTION = 5;
    public static final int REMOVE_ACTION = 6;
    public static final int SET_PERIOD_ACTION = 7;

    public static final int SHIFT_MENU_SETTINGS = 7;
    public static final int SET_CATALOG_ACTION = 8;
    public static final int SET_TYPE_ACTION = 9;

    protected View view;
    protected int action;

    private static LocalDate periodStart;
    private static LocalDate periodEnd;
    private static ListTypes currentTypeList;
    private static AbstractTaskList taskList;

    static {
        setPeriodStart(LocalDate.MIN);
        setPeriodEnd(LocalDate.MAX);
        setCurrentTypeList(ListTypes.LINKED);
    }

    protected Controller(View view, int action){
        this.view = view;
        this.action = action;
    }

    public static void setPeriodStart(LocalDate period){
        Controller.periodStart =  period;
    }

    public static void setPeriodEnd(LocalDate period){
        Controller.periodEnd =  period;
    }

    public static String getCurrentCatalog(){
        return System.getProperty("user.dir");
    }

    public static String getPeriodEndStr(){
        return periodEnd.isEqual(LocalDate.MAX)?"<not set>":periodEnd.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public static String getPeriodStartStr(){
        return periodStart.isEqual(LocalDate.MIN)?"<not set>":periodStart.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public static void setCurrentTypeList(ListTypes currType){
        Controller.currentTypeList = currType;
    }

    public static ListTypes getCurrentTypeList(){
        return Controller.currentTypeList;
    }

    public static LocalDate getPeriodStart(){
        return periodStart;
    }

    public static LocalDate getPeriodEnd(){
        return periodEnd;
    }

    public static String getCurrentPeriodStr(){
        return periodStart.isEqual(periodEnd)?
                periodStart.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")):
                periodStart.isEqual(LocalDate.MIN) && periodEnd.isEqual(LocalDate.MAX)?"<not set>":"From "+periodStart.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))+" to "+periodEnd.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public static AbstractTaskList getTaskList() {
        return Controller.taskList;
    }

    protected static void setTaskList(AbstractTaskList taskList) {
        Controller.taskList = taskList;
    }

    public static void initialization() {
        Path path = Paths.get(getCurrentCatalog());

        File newFile = new File(path.toString(),"tasks_array.json");
        if (newFile.exists()){
            Controller.setCurrentTypeList(ListTypes.ARRAY);
        }

        Controller.setTaskList(TaskListFactory.createTaskList(Controller.getCurrentTypeList()));

        File currFile = new File(path.toString(),"tasks_"+TaskUtil.getPostFixOfFile()+".json");
        if (!currFile.exists()){
            TaskUtil.saveTasksToFile(Controller.getTaskList());
        } else {
            TaskUtil.readTasksFromCatalog(Controller.getTaskList(),path);
        }
    }

    public boolean canProcess(int action){return this.action == action;}

    public int process(AbstractTaskList taskList){
        view.printInfo(taskList);
        return view.readAction();
    }
}
