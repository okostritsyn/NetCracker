package ua.edu.sumdu.j2se.kostrytsyn.tasks;

import java.lang.annotation.Annotation;
import java.lang.annotation.Repeatable;

public abstract class AbstractTaskList {
    public int numOfElem;

    public abstract void add(Task task);
    public abstract boolean remove(Task task);
    public abstract int size();
    public abstract Task getTask(int index) throws IndexOutOfBoundsException;

}
