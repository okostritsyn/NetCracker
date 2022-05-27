package ua.edu.sumdu.j2se.kostrytsyn.tasks;

import java.lang.StringBuilder;
import java.util.Iterator;
import java.util.Objects;

public abstract class AbstractTaskList implements Iterable<Task>, Cloneable {
    public int numOfElem;
    private int hashOfList;

    public abstract void add(Task task);
    public abstract boolean remove(Task task);
    public abstract int size();
    public abstract Task getTask(int index) throws IndexOutOfBoundsException;

    @Override
    public abstract Iterator<Task> iterator();

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        int s = 1;
        int currentSize = size();
        for (Task currentTask:
                this) {
            if (currentTask == null){
                continue;
            }
            str.append(currentTask);
            if (currentSize > s){
                str.append(";");
            }
            s++;
        }
        return "{"+str+ "}";
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (getClass() != o.getClass()) return false;
        AbstractTaskList list = (AbstractTaskList) o;
        return toString().equals(list.toString());
    }

    @Override
    public int hashCode(){
        return hashOfList;
    }

    public void updateHashSum(){
        hashOfList = Objects.hash(this);
    }

    /**
     * Get array of tasks which can be done in interval from array {@link ArrayTaskList}.
     * @param from - interval in hours
     * @param to - interval in hours
     */
    public AbstractTaskList incoming(int from, int to){
        AbstractTaskList TaskArr = TaskListFactory.createTaskList(ListTypes.types.ARRAY);
        if (from > to) {
            return TaskArr;
        }
        for (Task currentTask:
             this) {
            if (currentTask == null){
                continue;
            }
            int nextTime = currentTask.nextTimeAfter(from);

            if (from <= nextTime&&nextTime <= to) {
                TaskArr.add(currentTask);
            }
        }
        return TaskArr;
    }
}
