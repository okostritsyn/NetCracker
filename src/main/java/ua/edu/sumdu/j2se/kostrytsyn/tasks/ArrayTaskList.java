package ua.edu.sumdu.j2se.kostrytsyn.tasks;

import java.util.Arrays;

/**
 * Class create array of tasks and methods to work with them.
 * @author Kostrytsyn Oleg
 *
 * @version 0.1
 */
public class ArrayTaskList {
    /** Create array of tasks. */
    private Task[] arrayTask = new Task[0];
    /** quantity task in the array  */
    public int numOfElem;

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
            int newCapacity = arrayTask.length + 10;
            arrayTask = Arrays.copyOf(arrayTask, newCapacity);
        }
        arrayTask[numOfElem] = task;
        numOfElem++;
        System.out.println("Elements after add -- "+Arrays.toString(arrayTask));
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
                numOfElem--;
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

    /**
     * Get array of tasks which can be done in interval from array {@link ArrayTaskList}.
     * @param from - interval in hours
     * @param to - interval in hours
     */
    public ArrayTaskList incoming(int from, int to){

        ArrayTaskList TaskArr = new ArrayTaskList();

        if (from > to) {
            return TaskArr;
        }

        for (Task currentTask:
                arrayTask) {
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

    private Task [] removeElement(Task [] arr, int index ){
        Task[] arrDestination = new Task[arr.length - 1];
        int remainingElements = arr.length - ( index + 1 );
        System.arraycopy(arr, 0, arrDestination, 0, index);
        System.arraycopy(arr, index + 1, arrDestination, index, remainingElements);
        System.out.println("Elements after remove -- "  + Arrays.toString(arrDestination));
        return arrDestination;
    }
}
