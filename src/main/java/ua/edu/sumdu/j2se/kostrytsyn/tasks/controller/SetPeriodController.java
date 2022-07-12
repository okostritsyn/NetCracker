package ua.edu.sumdu.j2se.kostrytsyn.tasks.controller;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.view.View;

import java.time.LocalDate;

public class SetPeriodController extends Controller{
    protected SetPeriodController(View view, int action) {
        super(view, action);
    }

    @Override
    public int process(AbstractTaskList taskList) {
        int currAction = super.process(taskList);
        if (currAction == 0) {
            return Controller.CHANGE_MENU_ACTION;
        } else if(currAction == 1){
            String stringDateStart = view.collectDataFromUser("Enter start time of the tasks (dd-mm-yyyy):",Controller.getPeriodStartStr());
            Controller.setPeriodStart(TaskUtil.parseDateFromString(view,stringDateStart));

            String stringDateEnd = view.collectDataFromUser("Enter end time of the tasks (dd-mm-yyyy):",Controller.getPeriodEndStr());
            Controller.setPeriodEnd(TaskUtil.parseDateFromString(view,stringDateEnd));
        } else {
            Controller.setPeriodStart(LocalDate.MIN);
            Controller.setPeriodEnd(LocalDate.MAX);
        }
        return Controller.CHANGE_MENU_ACTION;
    }
}
