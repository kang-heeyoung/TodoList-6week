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
   
    public TodoItem(String title, String desc, String date, String category, String due_date) {
    	this.title = title;
    	this.desc = desc;
    	this.current_date = date;
    	this.category = category;
    	this.due_date = due_date;
    }
    
    public TodoItem(String title, String desc, String category, String due_date) {
    	this.title = title;
    	this.desc = desc;
    	this.category = category;
    	this.due_date = due_date;
    	SimpleDateFormat transFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        this.current_date=transFormat.format(new Date());
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
    	return id+". "+"[" + getCategory() + "] "+getTitle() +" - "+ getDesc()+" - "+getDue_date()+" - "+getCurrent_date();
    }
}
