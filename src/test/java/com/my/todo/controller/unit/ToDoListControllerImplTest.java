package com.my.todo.controller.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.my.todo.controller.ToDoListControllerImpl;
import com.my.todo.model.ToDoItem;
import com.my.todo.service.ToDoItemServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ToDoListControllerImplTest {

    @InjectMocks
    ToDoListControllerImpl toDoListControllerImpl = new ToDoListControllerImpl();

    @Mock
    ToDoItemServiceImpl toDoListServiceImplMock;

    MockMvc mockMvc;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(toDoListControllerImpl).build();
    }

    @Test
    public void can_create_ToDoItem() throws Exception {
        String createToDoItemUri = "/todo/";

        ObjectMapper o = new ObjectMapper();
        ObjectNode json = o.createObjectNode();
        json.put("name", "Buy Grocery");
        json.put("priority", "1");
        json.put("status", "1");
        String toDoItemJson = o.writeValueAsString(json);

        when(toDoListServiceImplMock.createToDoItem(any(ToDoItem.class))).thenReturn(1L);
        mockMvc.perform(post(createToDoItemUri)
                .content(toDoItemJson)
                .accept(MediaType.TEXT_PLAIN_VALUE,MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is2xxSuccessful());

        verify(toDoListServiceImplMock,times(1)).createToDoItem(any(ToDoItem.class));
    }


    @Test
    public void create_ToDoItem_fails_without_todoitem_name() throws Exception {
        String createToDoItemUri = "/todo/";

        ObjectMapper o = new ObjectMapper();
        ObjectNode json = o.createObjectNode();
        json.put("priority", "1");
        json.put("status", "1");
        String toDoItemJson = o.writeValueAsString(json);

        when(toDoListServiceImplMock.createToDoItem(any(ToDoItem.class))).thenReturn(1L);
        mockMvc.perform(post(createToDoItemUri)
                .content(toDoItemJson)
                .accept(MediaType.TEXT_PLAIN_VALUE,MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is4xxClientError());

    }

}