package com.perepelitsya.frontent;

import com.perepelitsya.backend.Todo;
import com.perepelitsya.backend.TodoRepository;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by Andriu on 18.03.2017.
 */
@UIScope
@SpringComponent
public class TodoLayout extends VerticalLayout implements TodoChangeListner {

    @Autowired
    TodoRepository repo;
    private List<Todo> todos;

    @PostConstruct
    void init() {
        setWidth("80%");
        update();
    }

    private void update() {
        setTodos(repo.findAll());
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
        removeAllComponents();
        todos.forEach(todo -> addComponent(new TodoItemLayout(todo, this)));
    }

    public void add(Todo todo) {
        repo.save(todo);
        update();
    }

    public void deleteCompleted() {
        repo.deleteByDone(true);
        update();
    }

    @Override
    public void todoChanged(Todo todo) {
        add(todo);
    }
}
