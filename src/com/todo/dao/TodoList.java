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
		System.out.println("제목순으로 정렬하였습니다.");
		System.out.println("[전체 목록]");
		for (TodoItem item : list) {
			System.out.println("[" + item.getTitle() + "] " + item.getDesc()+" - "+item.getCurrent_date());
		}
		System.out.println();
	}
	
	public void reverseList() {
		System.out.println("제목역순으로 정렬하였습니다.");
		Collections.reverse(list);
		System.out.println("[전체 목록]");
		for (TodoItem item : list) {
			System.out.println("[" + item.getTitle() + "] " + item.getDesc()+" - "+item.getCurrent_date());
		}
		System.out.println();
	}

	public void sortByDate() {
		System.out.println("날짜순으로 정렬하였습니다.");
		Collections.sort(list, new TodoSortByDate());
		System.out.println("[전체 목록]");
		for (TodoItem item : list) {
			System.out.println("[" + item.getTitle() + "] " + item.getDesc()+" - "+item.getCurrent_date());
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
}
