package ua.edu.sumdu.j2se.kostrytsyn.tasks;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Class create array of tasks and methods to work with them.
 * @author Kostrytsyn Oleg
 *
 * @version 0.1
 */
public class ArrayTaskList extends AbstractTaskList {
    /**
     * Create array of tasks.
     */
    private Task[] arrayTask = new Task[0];

    private static class ArrayTaskListIterator implements Iterator<Task> {
        public ArrayTaskList current;
        public int currentIndex;
        Task data;

        // initialize pointer to head of the list for iteration
        public ArrayTaskListIterator(ArrayTaskList list) {
            current = list;
            currentIndex = 0;
        }

        // returns false if next element does not exist
        public boolean hasNext() {
            return currentIndex < current.size();
        }

        // return current data and update pointer
        public Task next() {
            data = current.getTask(currentIndex);
            currentIndex++;
            return data;
        }

        // implement if needed
        public void remove () throws IllegalStateException
        {
            if (current == null || data == null) {
                throw new IllegalStateException();
            }
            current.remove(data);
            currentIndex--;
        }
    }

    //for iterable interface
    public Iterator<Task> iterator() {
        return new ArrayTaskListIterator(this);
    }

    @Override
    public int hashCode(){
        return super.hashCode();
    }

    @Override
    public ArrayTaskList clone() throws CloneNotSupportedException {
        ArrayTaskList TaskList = (ArrayTaskList) super.clone();

        for(Iterator<Task> currIter = TaskList.iterator();currIter.hasNext();  )
        {
            currIter.next();
            currIter.remove();
        }

        for (Task currentTask :
                this) {
            if (currentTask == null) {
                continue;
            }
            TaskList.add((Task) currentTask.clone());
        }
        return TaskList;
    }

    /**
     * Add task to array {@link ArrayTaskList}.
     * Increase size of array if needed
     * @param task - Link on the task
     */
    public void add(Task task){
        if (task == null) {
            System.out.println("You cannot add null task!");
            return;
        }

        if (numOfElem==arrayTask.length) {
            int newCapacity = arrayTask.length + 1;
            arrayTask = Arrays.copyOf(arrayTask, newCapacity);
        }
        arrayTask[numOfElem] = task;
        numOfElem++;
        updateHashSum();
        System.out.println("Elements after add -- "+this);
    }

    /**
     * Remove task from array {@link ArrayTaskList}.
     * Decrease size of array if needed
     * @param task - Link on the task
     */
    public boolean remove(Task task){
        if (arrayTask == null) {
            return false;
        } else {
            int indexElementToBeDeleted  = -1;
            int i = 0;
            for (Task currentTask:
                    arrayTask) {
                if (currentTask == null){
                    continue;
                }else if (currentTask.equals(task)) {
                    indexElementToBeDeleted  = i;
                }
                i++;
            }
            if (indexElementToBeDeleted >= 0) {
                arrayTask = removeElement(arrayTask,indexElementToBeDeleted);
                //arrayTask[indexElementToBeDeleted] = null;
                numOfElem--;
                updateHashSum();
                return true;
            }
        }
        return false;
    }

    /**
     * Get current size of array {@link ArrayTaskList}.
     */
    public int size(){
        if (arrayTask == null) {
            return -1;
        } else {
            return numOfElem;
        }
    }

    /**
     * Get task from array on index{@link ArrayTaskList}.
     * @param index - Index of the task in array
     * @return Task
     */
    public Task getTask(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size()){
            throw new IndexOutOfBoundsException("Index Out Of Bounds");
        }
        if (arrayTask == null) {
            return null;
        } else {
            return arrayTask[index];
        }
    }

    private Task[] removeElement(Task[] arr, int index ){
        Task[] arrDestination = new Task[arr.length - 1];
        int remainingElements = arr.length - ( index + 1 );
        System.arraycopy(arr, 0, arrDestination, 0, index);
        System.arraycopy(arr, index + 1, arrDestination, index, remainingElements);
        return arrDestination;
    }
}
