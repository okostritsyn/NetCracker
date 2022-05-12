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

		/*
		//settings parameters
		Task1.setActive(true);
		Task2.setActive(true);
		Task3.setActive(true);
		Task4.setActive(true);

		System.out.println(Task1.getTitle());
		System.out.println(Task1.nextTimeAfter(11)); //12

		System.out.println(Task2.getTitle());
		System.out.println(Task2.nextTimeAfter(13)); //13

		System.out.println(Task3.getTitle());
		System.out.println(Task3.nextTimeAfter(14)); //16

		System.out.println(Task4.getTitle());
		System.out.println(Task4.nextTimeAfter(16)); // -1

		//changing configurations
		Task1.setTitle("let`s run!");
		Task1.setTime(9);

		Task2.setTitle("Keep running! Make a stop every hour");
		Task2.setTime(10,18,1);

		Task3.setTitle("You need to eat! Make a stop 2 hours");
		Task3.setTime(19,22,2);

		Task4.setTitle("it`s time to sleep");
		Task4.setTime(23);

		System.out.println("In what time you need to run?");
		System.out.println(Task1.getStartTime());

		System.out.println("When the next stop?");
		System.out.println(Task2.nextTimeAfter(Task1.getStartTime())); //10

		System.out.println("When i can eat after run?");
		System.out.println(Task3.nextTimeAfter(Task2.getStartTime()));

		System.out.println("And if i start eating after 20?");
		System.out.println(Task3.nextTimeAfter(20));

		System.out.println("In what time you need to finish eating?");
		System.out.println(Task3.getEndTime());

		Task4.setActive(false);

		System.out.println("Can i sleep?");
		System.out.println(Task4.isActive());
		*/
		}
}
