package com.todo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;


public class TodoMain {
	public static void start() {
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
//		l.importData("todolist.txt");
		
		boolean isList = false;
		boolean quit = false;
		boolean x = true;
	
		do {
			if(x) {
				Menu.displaymenu();
				x = false;
			}
			System.out.printf("Command > ");
			isList = false;
			String choice = sc.nextLine();
//			if(choice.contains("find")) {
//				String[] arr = choice.split(" ");
//				String keyword = arr[1];
//				TodoUtil.find(l, keyword);
//			}else {
//			
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

			case "ls_name":
				System.out.println("제목순으로 정렬하였습니다.");
				TodoUtil.listAll(l, "title", 1);
				break;

			case "ls_name_desc":
				System.out.println("제목역순으로 정렬하였습니다.");
				TodoUtil.listAll(l, "title", 0);
				break;
				
			case "ls_date":
				System.out.println("날짜순으로 정렬하였습니다.");
				TodoUtil.listAll(l, "due_date", 1);
				break;
			
			case "ls_date_desc":
				System.out.println("날짜역순으로 정렬하였습니다.");
				TodoUtil.listAll(l, "due_date", 0);
				break;

			case "exit":
				quit = true;
				break;
				
			case "find":
				String keyword = sc.nextLine().trim();
				TodoUtil.findList(l,keyword);
				break;
				
			case "ls_cate":
				TodoUtil.listCateAll(l);
				break;
				
			case "find_cate":
				String cate = sc.nextLine().trim();
				TodoUtil.findCateList(l, cate);
				break;
				
			case "comp":
				String n = sc.nextLine().trim();
				int num = Integer.parseInt(n);
				TodoUtil.comp(l,num);
				break;
				
			case "ls_comp":
				TodoUtil.compList(l);
				break;
				
			case "percent":
				String n1 = sc.nextLine().trim();
				int num1 = Integer.parseInt(n1);
				TodoUtil.percent(l, num1);
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
