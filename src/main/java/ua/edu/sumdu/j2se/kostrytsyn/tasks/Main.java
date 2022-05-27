package ua.edu.sumdu.j2se.kostrytsyn.tasks;

import java.util.Iterator;

public class Main {

	public static void main(String[] args) {

		//create new tasks
		Task task1 = new Task("test1", 12, 11, 2); //error
		Task task2 = new Task("test2", 12, 15, 1); //repeatable
		Task task3 = new Task("test3", 16); //simple
		Task task4 = new Task("test4", 12, 15, 4); //repeatable with interval bigger than end

		//settings parameters
		task1.setActive(true);
		task2.setActive(true);
		task3.setActive(true);
		task4.setActive(true);

		//LinkedTaskList TaskArr = new LinkedTaskList();
		AbstractTaskList TaskArr =  TaskListFactory.createTaskList(ListTypes.types.ARRAY);

		TaskArr.add(task1);
		TaskArr.add(task2);
		TaskArr.add(task3);
		TaskArr.add(task4);
		boolean status = TaskArr.remove(task1);
		System.out.println("Remove element status " + status + " new size:" + TaskArr.size() + " quantity " + TaskArr.numOfElem);

		System.out.println("All elements are " +TaskArr);

		AbstractTaskList TaskArrIncoming = TaskArr.incoming(10, 15);
		System.out.println("Found " + TaskArrIncoming.size() + " elements from " + 10 + " to " + 15);

		for (int i = 0; i < TaskArrIncoming.size(); i++) {
			Task currTask = TaskArrIncoming.getTask(i);
			if (currTask == null) {
				continue;
			}
			System.out.println(currTask.getTitle());
		}

		AbstractTaskList TaskArrSecond = TaskListFactory.createTaskList(ListTypes.types.LINKED);

		TaskArrSecond.add(task1);
		TaskArrSecond.add(task2);
		TaskArrSecond.add(task3);
		TaskArrSecond.add(task4);
		System.out.println(TaskArrSecond.size());

		for (Task currTask2:
			 TaskArrSecond) {
			if (currTask2 == null) {
				continue;
			}
			System.out.println(currTask2.getTitle());
		}

		for(Iterator<Task> currTask2 = TaskArrSecond.iterator();currTask2.hasNext();  )
		{
			Task data = currTask2.next();
			if (data == null) {
				continue;
			}
			System.out.println(data.getTitle());
		}

		System.out.println("hash code "+TaskArrSecond.hashCode());
	}
}
