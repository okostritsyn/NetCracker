package ua.edu.sumdu.j2se.kostrytsyn.tasks.controller;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.Task;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.view.View;

public class RemoveTaskController extends Controller{
    protected RemoveTaskController(View view, int action) {
        super(view, action);
    }

    private void removeTask(AbstractTaskList taskList,Task currTask) {
        taskList.remove(currTask);
    }

    @Override
    public int process(AbstractTaskList taskList) {
        int currAction = super.process(taskList);
        if (currAction != 0) {
            Task currTask = taskList.getTask(currAction-1);
            if (currTask == null) {
                System.out.println("There is no such task! enter correct number!");
                return process(taskList);
            }
            System.out.println("You select task: " + currTask);
              while(true){
                  String strAction = view.collectDataFromUser("Are you sure want to remove task? Y|N","N");
                  String currFormat = "\\w";
                  if(!strAction.matches(currFormat)){
                      System.out.println("incorrect input!");
                      continue;
                  }
                  if(!strAction.equals("Y") && !strAction.equals("N")){
                      System.out.println("incorrect input!");
                      continue;
                  }
                  if(strAction.equals("Y")){
                      removeTask(taskList,currTask);
                      System.out.println("Task was removed!");
                  }
                  break;
              }
        }
        return Controller.CHANGE_MENU_ACTION;
    }
}
