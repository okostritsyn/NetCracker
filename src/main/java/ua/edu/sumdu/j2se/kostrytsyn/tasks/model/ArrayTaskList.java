package ua.edu.sumdu.j2se.kostrytsyn.tasks.model;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

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
    public Stream<Task> getStream(){
        return Arrays.stream(arrayTask);
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

    public Task[] toArray(){
        return arrayTask;
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
            boolean elementToDeleteFound  = false;
            int i = 0;
            for (Task currentTask:
                    arrayTask) {
                if (currentTask == null){
                    continue;
                }else if (currentTask == task || currentTask.equals(task)) {
                    arrayTask[i] = null;
                    elementToDeleteFound = true;
                }
                i++;
            }
            arrayTask = removeNullElements(arrayTask);

            if (elementToDeleteFound) {
                arrayTask = removeNullElements(arrayTask);
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

    private Task[] removeNullElements(Task[] arr){
        Arrays.sort(arr, (o1, o2) -> {
            if (o1 == null && o2 == null) {
                return 0;
            }
            if (o1 == null) {
                return 1;
            }
            if (o2 == null) {
                return -1;
            }
            return 0;
        });

        int indexBeforeNull = -1;
        for (Task currTask: arr) {
            indexBeforeNull++;
            if (currTask == null) break;
        }
        if (indexBeforeNull==-1) return arr;

        Task[] arrDestination = new Task[indexBeforeNull];
        System.arraycopy(arr, 0, arrDestination, 0, indexBeforeNull);

         return arrDestination;
    }

}

