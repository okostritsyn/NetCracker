package ua.edu.sumdu.j2se.kostrytsyn.tasks;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		//create new tasks
		Task task1 = new Task("test1", LocalDateTime.of(2022,05,01,11,00,00,00),LocalDateTime.of(2022,05,01,15,00,00,00),2); //error
		Task task2 = new Task("test2", LocalDateTime.of(2022,05,01,12,00,00,00),LocalDateTime.of(2022,05,01,15,00,00,00),1); //repeatable
		Task task3 = new Task("test3", LocalDateTime.of(2022,05,01,16,00,00,00)); //simple
		Task task4 = new Task("test4", LocalDateTime.of(2022,05,01,12,00,00,00),LocalDateTime.of(2022,05,01,15,00,00,00),4); //repeatable with interval bigger than end

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

		Iterable<Task> iterTasks = Tasks.incoming(TaskArr,LocalDateTime.of(2022,05,01,10,00,00,00),  LocalDateTime.of(2022,05,01,15,00,00,00));

		//convert iterator to list
		List<Task> TaskArrIncoming = new ArrayList<>();
		for (Task currTasks:
			 iterTasks) {
			TaskArrIncoming.add(currTasks);
		}

		System.out.println("Found " + TaskArrIncoming.size() + " elements from " + 10 + " to " + 15);

		for (int i = 0; i < TaskArrIncoming.size(); i++) {
			Task currTask = TaskArrIncoming.get(i);
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

		System.out.println("calendar "+Tasks.calendar(TaskArrSecond,LocalDateTime.of(2022,01,01,11,00,00,00),LocalDateTime.of(2022,12,01,11,00,00,00)));
	}
}
