package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean isList = false;
		boolean quit = false;
		boolean x = true;
		TodoUtil.loadList(l, "todolist.txt");
		do {
			if(x) {
				Menu.displaymenu();
				x = false;
			}
			System.out.printf("Command > ");
			isList = false;
			String choice = sc.nextLine();
			switch (choice) {
			case "help":
				Menu.displaymenu();
				break;
			
			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;

			case "ls_name_asc":
				l.sortByName();
				l.listAll();
				break;

			case "ls_name_desc":
				l.sortByName();
				l.reverseList();
				break;
				
			case "ls_date":
				l.sortByDate();
				break;

			case "exit":
				quit = true;
				break;

			default:
				System.out.println("정확한 명령어를 입력하세요. (도움말 - help)");
				System.out.println();
				break;
			}
			
			if(isList) l.listAll();
		} while (!quit);
		TodoUtil.saveList(l, "todolist.txt");
	}
}
