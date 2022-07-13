package ua.edu.sumdu.j2se.kostrytsyn.tasks;

import ua.edu.sumdu.j2se.kostrytsyn.tasks.controller.Controller;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.controller.MainController;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.view.MainView;
import ua.edu.sumdu.j2se.kostrytsyn.tasks.view.View;

public class Main {

	public static void main(String[] args) {
		Controller.initialization();

		Notification notificationInTray = new Notification();
		notificationInTray.displayMessageInTray("Task manager","Let`s start!","Task manager");
		Controller.setNotificationTray(notificationInTray);

		View mainView = new MainView();
		Controller mainController = new MainController(Controller.getTaskList(),mainView);
		mainController.process(Controller.getTaskList());

		notificationInTray.closeTray();

		System.out.println("Good bye!");

	}
}
