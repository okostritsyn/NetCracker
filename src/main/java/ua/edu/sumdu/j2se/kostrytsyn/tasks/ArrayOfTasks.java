package ua.edu.sumdu.j2se.kostrytsyn.tasks;

abstract public class ArrayOfTasks {
    public int numOfElem;
    abstract public void add(Task task);
    abstract public boolean remove(Task task);
    abstract public int size(Task task);
    abstract public Task getTask(int index);
    abstract public ArrayOfTasks incoming(int from, int to);
}
