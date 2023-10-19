package com.todo.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.todo.app.entity.Todo;
import com.todo.app.mapper.TodoMapper;

@Controller
//このクラスから、Mapperインターフェースのメソッドを呼び出す
//メソッドによってDBからデータを取得し、そのデータを埋め込んでブラウザに表示する
public class TodoController {

	@Autowired
	TodoMapper todoMapper;

	//＠RequestMappingに対応するURLが入力された時にこのメソッドが呼び出される
	//今回は「http://localhost:8080/ 」
	//(value="/todo")の場合は「http://localhost:8080/todo 」になる    

	@RequestMapping(value = "/")
	public String index(Model model) {

		//List<Todo> list = todoMapper.selectAll();
		//todoMapper.selectAll()でインターフェースのメソッドを実行
		//xml内のSQLの結果を戻り値としてlistに格納

		List<Todo> list = todoMapper.selectIncomplete();
		List<Todo> doneList = todoMapper.selectComplete();

		model.addAttribute("todos", list);
		//listの値にtodosという名前を付けて、ビュー(index.html側)に渡す（Thymeleafの${todos}の部分）
		model.addAttribute("doneTodos", doneList);

		return "index"; //returnで先程作ったindex.htmlを指定
	}

	@RequestMapping(value = "/add")
	@ResponseBody
	public Todo add(Todo todo) {
		todoMapper.add(todo);
		return todo;
	}

	@RequestMapping(value = "/update")
	@ResponseBody
	public void update(Todo todo) {
		todoMapper.update(todo);
	}

	@RequestMapping(value = "/delete")
	@ResponseBody
	public void delete() {
		todoMapper.delete();
	}

}
