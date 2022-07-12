package ua.edu.sumdu.j2se.kostrytsyn.tasks;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.model.*;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.view.*;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.controller.*;

public class Main {

	public static void main(String[] args) {
		System.out.println("Let`s start!");
		AbstractTaskList taskList = TaskListFactory.createTaskList(Controller.getCurrentTypeList());
		View mainView = new MainView();
		Controller mainController = new MainController(taskList,mainView);
		mainController.process(taskList);
		System.out.println("Good bye!");
	}
}
