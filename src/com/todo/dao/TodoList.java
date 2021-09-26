package com.todo.dao;

import java.util.*;

import com.todo.service.TodoSortByDate;
import com.todo.service.TodoSortByName;

public class TodoList {
	private List<TodoItem> list;

	public TodoList() {
		this.list = new ArrayList<TodoItem>();
	}

	public void addItem(TodoItem t) {
		list.add(t);
	}

	public void deleteItem(TodoItem t) {
		list.remove(t);
	}

	void editItem(TodoItem t, TodoItem updated) {
		int index = list.indexOf(t);
		list.remove(index);
		list.add(updated);
	}

	public ArrayList<TodoItem> getList() {
		return new ArrayList<TodoItem>(list);
	}

	public void sortByName() {
		Collections.sort(list, new TodoSortByName());
	}

	public void listAll() {
		int i = 1;
		System.out.println("제목순으로 정렬하였습니다.");
		System.out.println("[전체 목록]");
		for (TodoItem item : list) {
			System.out.println(i+". "+"[" + item.getCategory() + "] "+item.getTitle() +" - "+ item.getDesc()+" - "+item.getDue_date()+" - "+item.getCurrent_date());
			i++;
		}
		System.out.println();
	}
	
	public void reverseList() {
		int i = 1;
		System.out.println("제목역순으로 정렬하였습니다.");
		Collections.reverse(list);
		System.out.println("[전체 목록]");
		for (TodoItem item : list) {
			System.out.println(i+". "+"[" + item.getCategory() + "] "+item.getTitle() +" - "+ item.getDesc()+" - "+item.getDue_date()+" - "+item.getCurrent_date());
			i++;
		}
		System.out.println();
	}

	public void sortByDate() {
		int i = 1;
		System.out.println("날짜순으로 정렬하였습니다.");
		Collections.sort(list, new TodoSortByDate());
		System.out.println("[전체 목록]");
		for (TodoItem item : list) {
			System.out.println(i+". "+"[" + item.getCategory() + "] "+item.getTitle() +" - "+ item.getDesc()+" - "+item.getDue_date()+" - "+item.getCurrent_date());
			i++;
		}
		System.out.println();
	}

	public int indexOf(TodoItem t) {
		return list.indexOf(t);
	}

	public Boolean isDuplicate(String title) {
		for (TodoItem item : list) {
			if (title.equals(item.getTitle())) return true;
		}
		return false;
	}
	
	public int listSize() {
		return list.size();
	}
	
	public void find(String keyword) {
		int i = 1;
		int total = 0;
		
		for (TodoItem item : list) {
			if(item.getTitle().contains(keyword)) {
				System.out.println(i+". "+"[" + item.getCategory() + "] "+item.getTitle() +" - "+ item.getDesc()+" - "+item.getDue_date()+" - "+item.getCurrent_date());
				total ++;
			} else if(item.getDesc().contains(keyword)) {
				System.out.println(i+". "+"[" + item.getCategory() + "] "+item.getTitle() +" - "+ item.getDesc()+" - "+item.getDue_date()+" - "+item.getCurrent_date());
				total++;
			}
			i++;
		}
		System.out.println("총 "+total+"개의 항목을 찾았습니다.");
		System.out.println();
	}
}
