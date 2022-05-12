package ua.edu.sumdu.j2se.kostrytsyn.tasks;

import java.util.Arrays;

public class ArrayTaskList {

    private Task[] arrayTask;

    public void add(Task task){
        if (arrayTask == null) {
            arrayTask = new Task[]{task};
        } else {
            int newCapacity = arrayTask.length + 1;
            arrayTask = Arrays.copyOf(arrayTask, newCapacity);
            arrayTask[arrayTask.length-1] = task;
        }
        System.out.println("Elements after add -- "+Arrays.toString(arrayTask));
    }

    public boolean remove(Task task){
        if (arrayTask == null) {
            return false;
        } else {
            int indexElementToBeDeleted  = -1;
            int i = 0;
            for (Task currentTask:
                    arrayTask) {
                if (currentTask.equals(task)) {
                    indexElementToBeDeleted  = i;
                }
                i++;
            }
            if (indexElementToBeDeleted >= 0) {
                arrayTask = removeElement(arrayTask,indexElementToBeDeleted);
                return true;
            }
        }
        return false;
    }

    public int size(){
        if (arrayTask == null) {
            return -1;
        } else {
            return arrayTask.length;
        }
    }

    public Task getTask(int index){
        if (arrayTask == null) {
            return null;
        } else {
            return arrayTask[index];
        }
    }

    public ArrayTaskList incoming(int from, int to){

        ArrayTaskList TaskArr = new ArrayTaskList();

        for (Task currentTask:
                arrayTask) {
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
