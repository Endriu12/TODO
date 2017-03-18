package com.perepelitsya.frontent;

import com.perepelitsya.backend.Todo;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Andriu on 18.03.2017.
 */
@SpringUI
public class TodoUI extends UI {

    private VerticalLayout root;

    @Autowired
    TodoLayout todoLayout;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setupLayout();
        addHeader();
        addForm();
        addTodoList();
        addDeleteButton();
    }

    private void setupLayout() {
        root = new VerticalLayout();
        root.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setContent(root);
    }

    private void addHeader() {

        Label header = new Label("Todo");
        header.addStyleName(ValoTheme.LABEL_H1);
        root.addComponent(header);

    }

    private void addTodoList() {
        root.addComponent(todoLayout);
    }

    private void addForm() {
        HorizontalLayout formLayout = new HorizontalLayout();
        formLayout.setWidth("80%");
        TextField task = new TextField("");
        task.focus();
        Button add = new Button("");
        formLayout.addComponentsAndExpand(task);
        formLayout.addComponent(add);
        root.addComponent(formLayout);
        add.addStyleName(ValoTheme.BUTTON_PRIMARY);
        add.setIcon(VaadinIcons.PLUS);

        add.addClickListener(click -> {
            todoLayout.add(new Todo(task.getValue()));
            task.setValue("");
            task.focus();
        });
        add.setClickShortcut(ShortcutAction.KeyCode.ENTER);
    }
    private void addDeleteButton() {
            Button deleteButton = new Button("Delete completed items");
            deleteButton.addClickListener(click->todoLayout.deleteCompleted());
            root.addComponent(deleteButton);
    }
}