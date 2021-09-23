package com.todo.service;

import java.util.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.printf("[�׸� �߰�]\n"+"���� > ");
		
		title = sc.nextLine();
		if (list.isDuplicate(title)) {
			System.out.printf("�ߺ��� �����Դϴ�. �ٸ� ������ �Է����ּ���.");
			System.out.println();
			return;
		}
		
		System.out.printf("���� > ");
		desc = sc.nextLine();
		System.out.println();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("\n"
				+ "[�׸� ����]\n");
		System.out.printf("������ �׸��� ������ �Է��Ͻÿ� > ");
		
		String title = sc.nextLine();
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[�׸� ����]");
		System.out.println("������ �׸��� ������ �Է��Ͻÿ� > ");
		String title = sc.nextLine().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("�������� �ʴ� �����Դϴ�.");
			System.out.println();
			return;
		}

		System.out.println("�� ���� > ");
		String new_title = sc.nextLine().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("�ߺ��� �����Դϴ�.");
			System.out.println();
			return;
		}
		
		System.out.println("�� ���� > ");
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("�����Ǿ����ϴ�.");
				System.out.println();
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("[��ü ���]");
		for (TodoItem item : l.getList()) {
			System.out.println("[" + item.getTitle() + "] " + item.getDesc()+" - "+item.getCurrent_date());
		}
		System.out.println();
	}
	
	public static void saveList(TodoList l, String filename) {
		String filePath = filename;
		try {
			FileWriter fileWriter = new FileWriter(filePath);
			for(TodoItem item : l.getList()) {
				fileWriter.write(item.getTitle()+"##"+item.getDesc()+"##"+item.getCurrent_date()+"\n");
			}
			fileWriter.close();
		}catch(IOException e) {
			System.out.println("IOExcption");
		}
		
	}
	
	public static void loadList(TodoList l, String filename) {
		String filePath = filename;
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String oneline;
			while((oneline = br.readLine()) !=null) {
				StringTokenizer st = new StringTokenizer(oneline, "##");
				String title = st.nextToken();
				String item = st.nextToken();
				String date = st.nextToken();
				
				TodoItem t = new TodoItem(title, item, date);
				l.addItem(t);
			}
			br.close();
		} catch(FileNotFoundException e) {
			System.out.println("�ҷ��� ������ �����ϴ�.");
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
