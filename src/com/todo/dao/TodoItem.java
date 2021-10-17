package com.todo.dao;

import java.util.Date;
import java.text.SimpleDateFormat;

public class TodoItem {
    private String title;
    private String desc;
    private String current_date;
    private String category;
    private String due_date;
    private int id;
    private String is_completed;
    private String percent;
    private String with;
    
    public TodoItem(String title, String desc, String date, String category, String due_date, String is_completed, String percent, String with) {
    	this.title = title;
    	this.desc = desc;
    	this.current_date = date;
    	this.category = category;
    	this.due_date = due_date;
    	this.is_completed = is_completed;
    	this.percent = percent;
    	this.with = with;
    }
   
    public TodoItem(String title, String desc, String date, String category, String due_date, String is_completed) {
    	this.title = title;
    	this.desc = desc;
    	this.current_date = date;
    	this.category = category;
    	this.due_date = due_date;
    	this.is_completed = is_completed;
    }
    
    public TodoItem(String title, String desc, String category, String date, String with) {
    	this.title = title;
    	this.desc = desc;
    	this.due_date = date;
    	this.category = category;
    	this.with = with;
    	SimpleDateFormat transFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	this.current_date=transFormat.format(new Date());
    }
    
    public TodoItem(String title, String desc, String category, String due_date) {
    	this.title = title;
    	this.desc = desc;
    	this.category = category;
    	this.due_date = due_date;
    	SimpleDateFormat transFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        this.current_date=transFormat.format(new Date());
    }
    
    public TodoItem(int id, String percent) {
    	this.percent = percent;
    }
    
    public TodoItem(String is_completed) {
    	this.is_completed = is_completed;
    }
    
    public String getCategory() {
    	return category;
    }
    
    public void setCategory(String category) {
    	this.category = category;
    }
    
    public String getDue_date() {
    	return due_date;
    }
    
    public void setDue_date(String due_date) {
    	this.due_date = due_date;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    public String getIsCompleted() {
    	return is_completed;
    }
    
    public void setIsCompleted(String isCompleted) {
    	this.is_completed = isCompleted;
    }
    
    public String getPercent() {
    	return percent;
    }
    
    public void setPercent(String percent) {
    	this.percent = percent;
    }

    public String getWith() {
    	return with;
    }
    
    public void setWith(String with) {
    	this.with = with;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }
    
    public String toSaveString() {
    	return title + "##" + desc + "##" + current_date + "\n";
    }
    
    public String toString() {
    	if(getWith().equals("0")) {
    		if(((getIsCompleted().toString()).equals("1")) || getPercent().toString().equals("100")) {
        		return id+". "+"[" + getCategory() + "] "+getTitle() +"[V] - "+ getDesc()+" - "+getDue_date()+" - "+getCurrent_date();
        	}
        	else {
        		return id+". "+"[" + getCategory() + "] "+getTitle() +"["+getPercent()+"%]  - "+ getDesc()+" - "+getDue_date()+" - "+getCurrent_date();
        	}
    	}else {
    		if(((getIsCompleted().toString()).equals("1")) || getPercent().toString().equals("100")) {
        		return id+". "+"[" + getCategory() + "] "+getTitle() +"[V] - "+ getDesc()+" - "+getWith()+" - "+getDue_date()+" - "+getCurrent_date();
        	}
        	else {
        		return id+". "+"[" + getCategory() + "] "+getTitle() +"["+getPercent()+"%]  - "+ getDesc()+" - "+getWith()+" - "+getDue_date()+" - "+getCurrent_date();
        	}
    	}
    }
}
