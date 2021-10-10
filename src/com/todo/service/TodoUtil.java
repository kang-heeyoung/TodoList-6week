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
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.printf("[�׸� �߰�]\n"+"���� > ");
		
		title = sc.nextLine();
		if (list.isDuplicate(title)) {
			System.out.printf("������ �ߺ��˴ϴ�!");
			System.out.println();
			return;
		}
		System.out.printf("ī�װ� > ");
		category = sc.nextLine();
		
		System.out.printf("���� > ");
		desc = sc.nextLine();
		
		System.out.printf("�������� > ");
		due_date = sc.nextLine();
		System.out.println();
		
		TodoItem t = new TodoItem(title, desc, category, due_date);
		if(list.addItem(t)>0)
			System.out.println("�߰��Ǿ����ϴ�.");
	}

	public static void deleteItem(TodoList l) {
		int i = 1;
		Scanner sc = new Scanner(System.in);
		System.out.println("\n"
				+ "[�׸� ����]\n");
		System.out.printf("������ �׸��� ��ȣ�� �Է��Ͻÿ� > ");
		System.out.println();
		
		String num_s = sc.nextLine();
		int num = Integer.parseInt(num_s);
		if(l.deleteItem(num)>0)
			System.out.println("�����Ǿ����ϴ�.");
	}


	public static void updateItem(TodoList l) {
		int i = 1;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[�׸� ����]");
		System.out.println("������ �׸��� ��ȣ�� �Է��Ͻÿ� > ");
		String s = sc.nextLine();
		int num = Integer.parseInt(s);
		for(TodoItem item : l.getList()) {
			if(num==i) {
				System.out.println(i+". "+"[" + item.getCategory() + "] "+item.getTitle() +" - "+ item.getDesc()+" - "+item.getDue_date()+" - "+item.getCurrent_date());
			}
			i++;
		}
		i=1;
		System.out.printf("�� ���� > ");
		String new_title = sc.nextLine().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("�ߺ��� �����Դϴ�.");
			System.out.println();
			return;
		}
		System.out.printf("�� ī�װ� >");
		String new_category = sc.nextLine().trim();
		
		System.out.printf("�� ���� >  ");
		String new_description = sc.nextLine().trim();
		
		System.out.printf("�� �������� > ");
		String new_due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(new_title, new_description, new_category, new_due_date);
		t.setId(num);
		if(l.editItem(t)>0)
			System.out.println("�����Ǿ����ϴ�.");
//		for (TodoItem item : l.getList()) {
//			if (num==i) {
//				l.deleteItem(item);
//				TodoItem t = new TodoItem(new_title, new_description, new_category, new_due_date);
//				l.addItem(t);
//				System.out.println("�����Ǿ����ϴ�.");
//				System.out.println();
//				break;
//			}
//			i++;
//		}
	}
	public static void listAll(TodoList l) {
		System.out.printf("[��ü ���, ");
		System.out.println("�� "+l.getCount()+"��]");
		int i = 1;
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
//			System.out.println(i+". "+"[" + item.getCategory() + "] "+item.getTitle() +" - "+ item.getDesc()+" - "+item.getDue_date()+" - "+item.getCurrent_date());
			i++;
		}
		System.out.println();
	}

	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.printf("[��ü ���, ");
		System.out.println("�� "+l.getCount()+"��]");
		int i = 1;
		for (TodoItem item : l.getOrderedList(orderby, ordering)) {
			System.out.println(item.toString());
//			System.out.println(i+". "+"[" + item.getCategory() + "] "+item.getTitle() +" - "+ item.getDesc()+" - "+item.getDue_date()+" - "+item.getCurrent_date());
			i++;
		}
		System.out.println();
	}
	
	public static void findList(TodoList l, String keyword) {
		int count =0;
		for (TodoItem item : l.getList(keyword)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("�� %d���� �׸��� ã�ҽ��ϴ�.\n", count);
	}
	
	public static void listCateAll(TodoList l) {
		int count =0;
		for (String item : l.getCategories()) {
			System.out.print(item + " ");
			count++;
		}
		System.out.printf("\n�� %d���� ī�װ��� ��ϵǾ� �ֽ��ϴ�.\n", count);
	}
	
	public static void findCateList(TodoList l, String cate) {
		int count =0;
		for (TodoItem item:l.getListCategory(cate)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("\n�� %d���� �׸��� ã�ҽ��ϴ�.\n", count);
	}
	
	public static void saveList(TodoList l, String filename) {
		String filePath = filename;
		try {
			FileWriter fileWriter = new FileWriter(filePath);
			for(TodoItem item : l.getList()) {
				fileWriter.write(item.getCategory()+"##"+item.getTitle()+"##"+item.getDesc()+"##"+item.getDue_date()+"##"+item.getCurrent_date()+"\n");
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
				String category = st.nextToken();
				String title = st.nextToken();
				String item = st.nextToken();
				String due_date = st.nextToken();
				String date = st.nextToken();
				
				TodoItem t = new TodoItem(title, item, date, category, due_date);
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
