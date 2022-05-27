package ua.edu.sumdu.j2se.kostrytsyn.tasks;

import java.util.Iterator;

/**
 * Class create singly linked list of tasks and methods to work with them.
 * @author Kostrytsyn Oleg
 *
 * @version 0.1
 */
public class LinkedTaskList extends AbstractTaskList {
    private Node head;

    private static class Node{
        private final Task data;
        private Node next;

        public Node(Task data) {
            this.data = data;
            this.next = null;
        }
    }

    private static class LinkedTaskListIterator implements Iterator<Task> {
        public Node current;
        public LinkedTaskList list;
        public Task data;

        // initialize pointer to head of the list for iteration
        public LinkedTaskListIterator(LinkedTaskList list)
        {
            this.list = list;
            current = list.head;
            data = null;
        }

        // returns false if next element does not exist
        public boolean hasNext()
        {
            return current != null;
        }

        // return current data and update pointer
        public Task next()
        {
            data = current.data;
            current = current.next;
            return data;
        }

        // implement if needed
        public void remove () throws IllegalStateException
        {
            if (current == null || data == null) {
                throw new IllegalStateException();
            }
            list.remove(data);
        }
    }

    //for iterable interface
    public Iterator<Task> iterator() {
        return new LinkedTaskListIterator(this);
    }

    @Override
    public int hashCode(){
        return super.hashCode();
    }

    @Override
    public LinkedTaskList clone() throws CloneNotSupportedException {
        LinkedTaskList TaskList = new LinkedTaskList();

        for (Task currentTask:
                this) {
            if (currentTask == null){
                continue;
            }
            TaskList.add(currentTask);
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

        Node newNode = new Node(task);
        Node currNode = head;

        if (currNode == null) {
            head = newNode;
        } else {
            while (currNode.next != null) {
                currNode = currNode.next;
            }
            currNode.next = newNode;
        }
        numOfElem++;
        updateHashSum();
        System.out.println("Elements after add -- "+ this);
    }

    /**
     * Remove task from array {@link ArrayTaskList}.
     * Decrease size of array if needed
     * @param task - Link on the task
     */
    public boolean remove(Task task){
        if (head != null) {
            boolean elementHasFound = false;
            Node currNode = head;
            Node prevNode = null;

            do {
                if(currNode.data == task){
                    if(currNode == head){
                        head = currNode.next;
                    }else{
                        prevNode.next = currNode.next;
                    }
                    elementHasFound = true;
                    break;
                }
                prevNode = currNode;
                currNode = currNode.next;
            } while (currNode != null);

            if (elementHasFound){
                numOfElem--;
                updateHashSum();
                System.out.println("Elements after remove -- "  + this);
                return true;
            }
        }
        return false;
    }

    /**
     * Get current size of array {@link ArrayTaskList}.
     */
    public int size(){
        return numOfElem;
    }

    /**
     * Get task from array on index{@link ArrayTaskList}.
     * @param index - Index of the task in array
     * @return Task - object of Task
     */
    public Task getTask(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size()){
            throw new IndexOutOfBoundsException("Index Out Of Bounds");
        }
        if (head == null) {
            return null;
        } else {
            Node currNode = head;
            int i = 0;
            while (currNode.next != null) {
                if (i==index){
                   break;
                }
                i++;
                currNode = currNode.next;
            }
            return currNode.data;
        }
    }
}