package com.todo.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.todo.app.entity.Todo;

@Mapper
public interface TodoMapper {

	public List<Todo> selectAll();

	public List<Todo> selectIncomplete();//未完了のみ表示

	public List<Todo> selectComplete();//完了のみ表示

	public void add(Todo todo); // 追加

	public void update(Todo todo); //更新
	
	public void delete(); //削除
}