package ua.edu.sumdu.j2se.kostrytsyn.tasks;

public class TaskListFactory {
    public static AbstractTaskList createTaskList(ListTypes.types type){
        AbstractTaskList currArray = null;
        if (type == ListTypes.types.ARRAY) {
            currArray = new ArrayTaskList();
        } else {
            currArray = new LinkedTaskList();
        }
        return currArray;
    }
}
