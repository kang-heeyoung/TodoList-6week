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
		
		System.out.printf("[항목 추가]\n"+"제목 > ");
		
		title = sc.nextLine();
		if (list.isDuplicate(title)) {
			System.out.printf("중복된 제목입니다. 다른 제목을 입력해주세요.");
			System.out.println();
			return;
		}
		
		System.out.printf("내용 > ");
		desc = sc.nextLine();
		System.out.println();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("\n"
				+ "[항목 삭제]\n");
		System.out.printf("삭제할 항목의 제목을 입력하시오 > ");
		
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
		
		System.out.println("[항목 수정]");
		System.out.println("수정할 항목의 제목을 입력하시오 > ");
		String title = sc.nextLine().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("존재하지 않는 제목입니다.");
			System.out.println();
			return;
		}

		System.out.println("새 제목 > ");
		String new_title = sc.nextLine().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("중복된 제목입니다.");
			System.out.println();
			return;
		}
		
		System.out.println("새 내용 > ");
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("수정되었습니다.");
				System.out.println();
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("[전체 목록]");
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
			System.out.println("불러올 파일이 없습니다.");
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
