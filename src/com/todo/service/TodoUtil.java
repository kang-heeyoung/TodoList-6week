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
		
		System.out.printf("[항목 추가]\n"+"제목 > ");
		
		title = sc.nextLine();
		if (list.isDuplicate(title)) {
			System.out.printf("제목이 중복됩니다!");
			System.out.println();
			return;
		}
		System.out.printf("카테고리 > ");
		category = sc.nextLine();
		
		System.out.printf("내용 > ");
		desc = sc.nextLine();
		
		System.out.printf("마감일자 > ");
		due_date = sc.nextLine();
		System.out.println();
		
		TodoItem t = new TodoItem(title, desc, category, due_date);
		if(list.addItem(t)>0)
			System.out.println("추가되었습니다.");
	}

	public static void deleteItem(TodoList l) {
		int i = 1;
		Scanner sc = new Scanner(System.in);
		System.out.println("\n"
				+ "[항목 삭제]\n");
		System.out.printf("삭제할 항목의 번호를 입력하시오 > ");
		System.out.println();
		
		String num_s = sc.nextLine();
		int num = Integer.parseInt(num_s);
		if(l.deleteItem(num)>0)
			System.out.println("삭제되었습니다.");
	}


	public static void updateItem(TodoList l) {
		int i = 1;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[항목 수정]");
		System.out.println("수정할 항목의 번호를 입력하시오 > ");
		String s = sc.nextLine();
		int num = Integer.parseInt(s);
		for(TodoItem item : l.getList()) {
			if(num==i) {
				System.out.println(i+". "+"[" + item.getCategory() + "] "+item.getTitle() +" - "+ item.getDesc()+" - "+item.getDue_date()+" - "+item.getCurrent_date());
			}
			i++;
		}
		i=1;
		System.out.printf("새 제목 > ");
		String new_title = sc.nextLine().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("중복된 제목입니다.");
			System.out.println();
			return;
		}
		System.out.printf("새 카테고리 >");
		String new_category = sc.nextLine().trim();
		
		System.out.printf("새 내용 >  ");
		String new_description = sc.nextLine().trim();
		
		System.out.printf("새 마감일자 > ");
		String new_due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(new_title, new_description, new_category, new_due_date);
		t.setId(num);
		if(l.editItem(t)>0)
			System.out.println("수정되었습니다.");
//		for (TodoItem item : l.getList()) {
//			if (num==i) {
//				l.deleteItem(item);
//				TodoItem t = new TodoItem(new_title, new_description, new_category, new_due_date);
//				l.addItem(t);
//				System.out.println("수정되었습니다.");
//				System.out.println();
//				break;
//			}
//			i++;
//		}
	}
	public static void listAll(TodoList l) {
		System.out.printf("[전체 목록, ");
		System.out.println("총 "+l.getCount()+"개]");
		int i = 1;
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
//			System.out.println(i+". "+"[" + item.getCategory() + "] "+item.getTitle() +" - "+ item.getDesc()+" - "+item.getDue_date()+" - "+item.getCurrent_date());
			i++;
		}
		System.out.println();
	}

	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.printf("[전체 목록, ");
		System.out.println("총 "+l.getCount()+"개]");
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
		System.out.printf("총 %d개의 항목을 찾았습니다.\n", count);
	}
	
	public static void listCateAll(TodoList l) {
		int count =0;
		for (String item : l.getCategories()) {
			System.out.print(item + " ");
			count++;
		}
		System.out.printf("\n총 %d개의 카테고리가 등록되어 있습니다.\n", count);
	}
	
	public static void findCateList(TodoList l, String cate) {
		int count =0;
		for (TodoItem item:l.getListCategory(cate)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("\n총 %d개의 항목을 찾았습니다.\n", count);
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
			System.out.println("불러올 파일이 없습니다.");
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
