package ua.edu.sumdu.j2se.kostrytsyn.tasks;

import java.util.Iterator;

/**
 * Class create singly linked list of tasks and methods to work with them.
 * @author Kostrytsyn Oleg
 *
 * @version 0.1
 */
public class LinkedTaskList_iterator extends AbstractTaskList implements Iterable {
    private Node head;
    /** quantity task in the array  */

    private static class Node{
        private final Task data;
        private Node next;

        public Node(Task data) {
            this.data = data;
            this.next = null;
        }
    }

    private static class LinkedTaskListIterator implements Iterator {
        public Node current;

        // initialize pointer to head of the list for iteration
        public LinkedTaskListIterator(LinkedTaskList_iterator list)
        {
            Node current = list.head;
        }

        // returns false if next element does not exist
        public boolean hasNext()
        {
            return current != null;
        }

        // return current data and update pointer
        public Task next()
        {
            Task data = current.data;
            current = current.next;
            return data;
        }

        // implement if needed
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public String toString() {
        return printLinkedList(head);
    }

    //for iterable interface
    public Iterator iterator() {
        return new LinkedTaskListIterator(this);
    }

    private String printLinkedList(Node startNode) {
        StringBuilder str = new StringBuilder();
        Node currNode = startNode;
        do {
            if(currNode == null) break;
            str.append(currNode.data.toString());
            currNode = currNode.next;
        } while (currNode != null);
        return "{"+str+ "}";
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
        System.out.println("Elements after add -- "+ printLinkedList(head));
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
                System.out.println("Elements after remove -- "  + printLinkedList(head));
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

    /**
     * Get array of tasks which can be done in interval from array {@link ArrayTaskList}.
     * @param from - interval in hours
     * @param to - interval in hours
     */
    public LinkedTaskList_iterator incoming(int from, int to){

        LinkedTaskList_iterator taskArr = new LinkedTaskList_iterator();

        if (from > to) {
            return taskArr;
        }

        //Node currNode = head;
        /*
        do {
            Task currentTask = currNode.data;
            int nextTime = currentTask.nextTimeAfter(from);
            if (from <= nextTime&&nextTime <= to) {
                taskArr.add(currentTask);
            }
            currNode = currNode.next;
        } while (currNode != null);
        */
        return taskArr;

    }
}