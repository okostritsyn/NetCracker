package ua.edu.sumdu.j2se.kostrytsyn.tasks;

public abstract class AbstractTaskList  {
    public int numOfElem;

    public abstract void add(Task task);
    public abstract boolean remove(Task task);
    public abstract int size();
    public abstract Task getTask(int index) throws IndexOutOfBoundsException;

    /**
     * Get array of tasks which can be done in interval from array {@link ArrayTaskList}.
     * @param from - interval in hours
     * @param to - interval in hours
     */
    public AbstractTaskList incoming(int from, int to){
        AbstractTaskList TaskArr = new ArrayTaskList();

        if (from > to) {
            return TaskArr;
        }
        for(int i = 0;i<size();i++){
            Task currentTask = getTask(i);
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
