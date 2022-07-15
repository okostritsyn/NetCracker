package ua.edu.sumdu.j2se.kostrytsyn.tasks.controller;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.Task;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.view.View;

public class TaskDataUtil {
    public static void setTitleOfTask(View view, Task currTask){
        String title = view.collectDataFromUser("Enter title of the task:",currTask.getTitle());
        if (title.isEmpty()) title = "<none>";
        currTask.setTitle(title);
    }

    public static void setActiveOfTask(View view, Task currTask){
        while(true){
            String strAction = view.collectDataFromUser("Make task active ? Y|N:",currTask.isActive()?"Y":"N");
            String currFormat = "\\w";
            if(!strAction.matches(currFormat)){
                System.out.println("incorrect input!");
                continue;
            }
            if(!strAction.equals("Y") && !strAction.equals("N")){
                System.out.println("incorrect input!");
                continue;
            }
            currTask.setActive(strAction.equals("Y"));
            break;
        }
    }

    public static void setStartTimeOfTask(View view,Task currTask){
        String stringDate = view.collectDataFromUser("Enter start time of the task (dd-mm-yyyy hh:mm):",currTask.getStartTimeStr());
        currTask.setStartTime(CollectDataUtil.parseDateTimeFromString(view,stringDate));
    }

    public static void setEndTimeOfTask(View view,Task currTask){
        String stringDate = view.collectDataFromUser("Enter end time of the task (dd-mm-yyyy hh:mm):",currTask.getEndTimeStr());
        currTask.setEndTime(CollectDataUtil.parseDateTimeFromString(view,stringDate));
    }

    public static void setInterval(View view,Task currTask){
        String strInterval = view.collectDataFromUser("Enter interval for repeatable tasks (hours)",Integer.toString(currTask.getRepeatInterval()));
        String currFormat = "\\d{1,2}";
        if(!strInterval.matches(currFormat)){
            System.out.println("incorrect number of hours!");
            setInterval(view,currTask);
            return;
        }

        int currInterval;
        try {
            currInterval = Integer.parseInt(strInterval);
        } catch (NumberFormatException e) {
            System.out.println("incorrect number of hours!");
            setInterval(view,currTask);
            return;
        }
        currTask.setInterval(currInterval);
    }

    public static void setTypeOfTask(View view,Task currTask){
        String strInterval = view.collectDataFromUser("Enter type of task (1-repeatable, 2-unrepeatable):",currTask.isRepeated()?"1":"2");
        String currFormat = "\\d";
        if(!strInterval.matches(currFormat)){
            System.out.println("incorrect type of task!");
            setTypeOfTask(view,currTask);
            return;
        }

        int currType;
        try {
            currType = Integer.parseInt(strInterval);
        } catch (NumberFormatException e) {
            System.out.println("incorrect type of task!");
            setTypeOfTask(view,currTask);
            return;
        }

        if (currType < 1 || currType > 2) {
            System.out.println("incorrect type of task!");
            setTypeOfTask(view,currTask);
            return;
        }

        currTask.setRepeated(currType == 1);
    }
}
