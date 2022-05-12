package ua.edu.sumdu.j2se.kostrytsyn.tasks;

import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		//create new tasks
		Task task1 = new Task("test1",12,11,2); //error
		Task task2 = new Task("test2",12,15,1); //repeatable
		Task task3 = new Task("test3",16); //simple
		Task task4 = new Task("test3",12,15,4); //repeatable with interval bigger than end

		//settings parameters
		task1.setActive(true);
		task2.setActive(true);
		task3.setActive(true);
		task4.setActive(true);

		System.out.println(task1.getTitle());
		System.out.println(task1.nextTimeAfter(11)); //12

		System.out.println(task2.getTitle());
		System.out.println(task2.nextTimeAfter(13)); //13

		System.out.println(task3.getTitle());
		System.out.println(task3.nextTimeAfter(14)); //16

		System.out.println(task4.getTitle());
		System.out.println(task4.nextTimeAfter(16)); // -1

		//changing configurations
		task1.setTitle("let`s run!");
		task1.setTime(9);

		task2.setTitle("Keep running! Make a stop every hour");
		task2.setTime(10,18,1);

		task3.setTitle("You need to eat! Make a stop 2 hours");
		task3.setTime(19,22,2);

		task4.setTitle("it`s time to sleep");
		task4.setTime(23);

		System.out.println("In what time you need to run?");
		System.out.println(task1.getStartTime());

		System.out.println("When the next stop?");
		System.out.println(task2.nextTimeAfter(task1.getStartTime())); //10

		System.out.println("When i can eat after run?");
		System.out.println(task3.nextTimeAfter(task2.getStartTime()));

		System.out.println("And if i start eating after 20?");
		System.out.println(task3.nextTimeAfter(20));

		System.out.println("In what time you need to finish eating?");
		System.out.println(task3.getEndTime());

		task4.setActive(false);

		System.out.println("Can i sleep?");
		System.out.println(task4.isActive());

		}
}
