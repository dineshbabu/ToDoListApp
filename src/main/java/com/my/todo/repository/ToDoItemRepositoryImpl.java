package com.my.todo.repository;

import com.my.todo.model.ToDoItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToDoItemRepositoryImpl implements ToDoItemRepository {

    List<ToDoItem> toDoItemList = new ArrayList<>();
    private static long id = 0;

    @Override
    public long createToDoItem(ToDoItem toDoItem) {
        setId(toDoItem);
        toDoItemList.add(toDoItem);
        return toDoItem.getId();
    }

    private void setId(ToDoItem toDoItem) {
        toDoItem.setId(++id);
    }

    @Override
    public long updateToDoItem(ToDoItem toDoItem) {
        toDoItemList = toDoItemList.stream()
                .map(toDoItem1 -> {
                    if(toDoItem1.getId() == toDoItem.getId()){
                        return toDoItem;
                    }
                    return toDoItem1;
                })
                .collect(Collectors.toList());
        return toDoItem.getId();
    }

    @Override
    public int deleteToDoItem(ToDoItem toDoItem) {
        return 0;
    }

    @Override
    public List<ToDoItem> getAllToDoItems() {
        return toDoItemList;
    }

    @Override
    public ToDoItem getToDOItem(int id) {
        return null;
    }

    @Override
    public List<ToDoItem> filterToDoItemsByStatus(int status) {
        return toDoItemList.stream()
                .filter(toDoItem -> toDoItem.getStatus() == status)
                .collect(Collectors.toList());
    }

    @Override
    public List<ToDoItem> filterToDoItemsByPriority(int priority) {
        return toDoItemList.stream()
                .filter(toDoItem -> toDoItem.getPriority() == priority)
                .collect(Collectors.toList());
    }
}
