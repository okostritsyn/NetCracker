package ua.edu.sumdu.j2se.kostrytsyn.tasks.controller;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.Task;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.view.View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class AddTaskController extends Controller{
    protected AddTaskController(View view, int action) {
        super(view, action);
    }

    private String getTitleOfTask(){
        String title = view.collectDataFromUser("Enter title of new task:");
        if (title.isEmpty()) title = "<none>";
        return title;
    }

    private LocalDateTime getStartTimeOfTask(){
        String stringDate = view.collectDataFromUser("Enter start time of the new task (dd-mm-yyyy hh:mm):");
        return parseDateFromString(stringDate);
    }

    private LocalDateTime convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    private LocalDateTime parseDateFromString(String stringDate){
        String currFormat = "\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}";
        if(!stringDate.matches(currFormat)){
            System.out.println("incorrect date! Insert date in format dd-mm-yyyy hh:mm");
            return parseDateFromString(view.readInputString());
        }
        try {
            Date date=new SimpleDateFormat("dd-MM-yyyy hh:mm").parse(stringDate);
            return convertToLocalDateViaInstant(date);
        } catch (ParseException e) {
            System.out.println("incorrect date! Insert date in format dd-mm-yyyy hh:mm");
            return parseDateFromString(view.readInputString());
        }
    }

    private LocalDateTime getEndTimeOfTask(){
        String stringDate = view.collectDataFromUser("Enter end time of the new task (dd-mm-yyyy hh:mm):");
        return parseDateFromString(stringDate);
    }

    private int getInterval(){
        String strInterval = view.collectDataFromUser("Enter interval for repeatable tasks (hours)");
        String currFormat = "\\d{1,2}";
        if(!strInterval.matches(currFormat)){
            System.out.println("incorrect number of hours!");
            getInterval();
        }

        int currInterval;
        while(true){
            try {
                currInterval = Integer.parseInt(strInterval);
                break;
            } catch (NumberFormatException e) {
                System.out.println("incorrect number of hours!");
                getInterval();
            }
        }
        return currInterval;
    }

    private Task getNewTask(int typeOfTask) {
        String title = getTitleOfTask();
        LocalDateTime startTime = getStartTimeOfTask();
        if (typeOfTask == 1) {
            LocalDateTime endTime = getEndTimeOfTask();
            int currInterval = getInterval();
            return new Task(title, startTime, endTime, currInterval);
        } else {
            return new Task(title, startTime);
        }
    }

    @Override
    public int process(AbstractTaskList taskList) {
        int currAction = super.process(taskList);
        if (currAction != 3) {
            Task newTask = getNewTask(currAction);
            taskList.add(newTask);
            System.out.println("Task was added!");
        }
        return Controller.MAIN_MENU_ACTION;
    }
}
