package ua.edu.sumdu.j2se.kostrytsyn.tasks;

public class Main {

	public static void main(String[] args) {

		//create new tasks
		Task task1 = new Task("test1",12,11,2); //error
		Task task2 = new Task("test2",12,15,1); //repeatable
		Task task3 = new Task("test3",16); //simple
		Task task4 = new Task("test4",12,15,4); //repeatable with interval bigger than end

		//settings parameters
		task1.setActive(true);
		task2.setActive(true);
		task3.setActive(true);
		task4.setActive(true);

		LinkedTaskList TaskArr = new LinkedTaskList();
		TaskArr.add(task1);
		TaskArr.add(task2);
		TaskArr.add(task3);
		TaskArr.add(task4);
		boolean status = TaskArr.remove(task1);
		System.out.println("Remove element status "+status+" new size:"+TaskArr.size()+" quantity "+TaskArr.numOfElem);

		Task currentTask = TaskArr.getTask(0);
		System.out.println(currentTask.getTitle());

		LinkedTaskList TaskArrIncoming = TaskArr.incoming(10,15);
		System.out.println("Found "+TaskArrIncoming.size()+" elements from "+10+" to "+15);

		for (int i = 0;i < TaskArrIncoming.size();i++) {
			Task currTask = TaskArrIncoming.getTask(i);
			if (currTask == null){
				continue;
			}
			System.out.println(currTask.getTitle());
		}
		System.out.println(TaskArr);
	}
}
