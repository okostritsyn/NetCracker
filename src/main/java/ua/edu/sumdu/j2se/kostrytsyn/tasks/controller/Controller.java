package ua.edu.sumdu.j2se.kostrytsyn.tasks.controller;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.exceptions.EmptyListOfControllersException;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.ListTypes;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.Task;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.TaskListFactory;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.notifications.Notification;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.notifications.NotificationInTray;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.view.View;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

    private static Notification notification;
    private static ArrayList<RunTaskController> runTasks;

    static {
        setPeriodStart(LocalDate.MIN);
        setPeriodEnd(LocalDate.MAX);
        setCurrentTypeList(ListTypes.LINKED);
    }

    protected Controller(View view, int action){
        this.view = view;
        this.action = action;
    }

    private static void initNotification() {
        Controller.notification = new NotificationInTray();
        Controller.notification.init();
        Controller.notification.displayMessage("Task manager","Let`s start!");
    }

    public static Notification getNotification() {
        return Controller.notification;
    }

    public static void setPeriodStart(LocalDate period){
        Controller.periodStart =  period;
    }

    public static void addRunTaskController(RunTaskController runTask){
        if (runTask != null) Controller.runTasks.add(runTask);
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
        initNotification();

        initTypeofList();

        readTasks();

        initScheduler();
    }

    private static void readTasks() {
        Path path = Paths.get(getCurrentCatalog());

        File currFile = new File(path.toString(),"tasks_"+ IOUtil.getPostFixOfFile()+".json");
        if (!currFile.exists()){
            IOUtil.saveTasksToFile(Controller.getTaskList());
        } else {
            IOUtil.readTasksFromCatalog(Controller.getTaskList(),path);
        }
    }

    private static void initScheduler() {
        runTasks = new ArrayList<>(Controller.getTaskList().size() == 0?10:Controller.getTaskList().size()*2);

        for (Task currTask:
                Controller.getTaskList()) {
            if (!currTask.isActive()) continue;
            BackgroundJobManager jobManager = new BackgroundJobManager();
            addRunTaskController(jobManager.init(currTask));
        }
    }

    private static void closeScheduler() {
        for (RunTaskController currScheduler:Controller.RunTaskControllers()) {
            if (currScheduler == null) continue;
            if (!currScheduler.getManager().isShutdown())
                currScheduler.getManager().shutdownNow();
        }
    }

    private static void initTypeofList() {
        Path path = Paths.get(getCurrentCatalog());

        File newFile = new File(path.toString(),"tasks_array.json");
        if (newFile.exists()){
            Controller.setCurrentTypeList(ListTypes.ARRAY);
        }

        Controller.setTaskList(TaskListFactory.createTaskList(Controller.getCurrentTypeList()));
    }

    public static ArrayList<RunTaskController> RunTaskControllers() {
        return Controller.runTasks;
    }

    public static void closeNotification() {
        Controller.notification.close();
    }

    public static void finishWork() {
        IOUtil.saveTasksToFile(Controller.getTaskList());

        closeScheduler();

        closeNotification();
    }

    public boolean canProcess(int action){return this.action == action;}

    public int process(AbstractTaskList taskList){
        view.printInfo(taskList);
        return view.readAction();
    }

    public int processMenu(int action,int finishAction,List<Controller>  controllers) throws EmptyListOfControllersException {
        if (controllers.size() == 0){
            throw new EmptyListOfControllersException("There is no controllers to process! ");
        }
        do {
            for (Controller controller : controllers) {
                if (controller.canProcess(action)) {
                    action = controller.process(Controller.getTaskList());
                }
            }
        } while (action != finishAction);
        return action;
    }
}
