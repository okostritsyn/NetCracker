package ua.edu.sumdu.j2se.kostrytsyn.tasks;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {

		//create new tasks
		Task task1 = new Task("test1",12,11,2); //error
		Task task2 = new Task("test2",12,15,1); //repeatable
		Task task3 = new Task("test3",16); //simple
		Task task4 = new Task("test3",12,15,4); //repeatable with interval bigger than end

		ArrayTaskList TaskArr = new ArrayTaskList();
		System.out.println(TaskArr.size());
		TaskArr.add(task1);
		System.out.println(TaskArr.size());
		TaskArr.add(task2);
		System.out.println(TaskArr.size());
		TaskArr.add(task3);
		System.out.println(TaskArr.size());

		boolean status = TaskArr.remove(task1);
		System.out.println("Remove element status "+status+" new size:"+TaskArr.size());

		Task currentTask = TaskArr.getTask(0);
		System.out.println(currentTask.getTitle());

		}
}
