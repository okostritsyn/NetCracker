package ua.edu.sumdu.j2se.kostrytsyn.tasks.model;

public class TaskListFactory {
    public static AbstractTaskList createTaskList(ListTypes type){
        if (type == ListTypes.ARRAY) {
            return new ArrayTaskList();
        } else {
            return new LinkedTaskList();
        }
    }
}
