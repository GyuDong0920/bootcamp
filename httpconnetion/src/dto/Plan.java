package dto;

import java.util.ArrayList;
import java.util.List;

class Todo{
	int id;
	String title;
	boolean complete;
	
}

public class Plan {

	private List<Todo> todoList = new ArrayList<Todo>(); 
	private String server_name;
	
	// todoList, get, set
	public List<Todo> getTodoList() {
		return todoList;
	}
	public void setTodoList(List<Todo> todoList) {
		this.todoList = todoList;
	}
}
