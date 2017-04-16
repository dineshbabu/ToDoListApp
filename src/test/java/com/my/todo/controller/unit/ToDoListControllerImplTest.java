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

import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ToDoListControllerImplTest {

    @InjectMocks
    ToDoListControllerImpl toDoListControllerImpl = new ToDoListControllerImpl();

    @Mock
    ToDoItemServiceImpl toDoListServiceImplMock;

    MockMvc mockMvc;

    @Mock
    private List<ToDoItem> toDoItemsListMock;

    @Mock
    private ObjectMapper objectMapperMock;
    private String dummyJson = "{dummyKey:dummyValue}";

    @Mock
    private ToDoItem toDoItemMock;

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
        verify(toDoListServiceImplMock,times(0)).createToDoItem(any(ToDoItem.class));
    }

    @Test
    public void can_update_ToDoItem() throws Exception {
        String updateToDoItemUri = "/todo/1";

        ObjectMapper o = new ObjectMapper();
        ObjectNode json = o.createObjectNode();
        json.put("name", "Buy Grocery");
        json.put("priority", "1");
        json.put("status", "1");
        json.put("id", "1");
        String toDoItemJson = o.writeValueAsString(json);

        when(toDoListServiceImplMock.updateToDoItem(any(ToDoItem.class))).thenReturn(1L);
        mockMvc.perform(put(updateToDoItemUri)
                .content(toDoItemJson)
                .accept(MediaType.TEXT_PLAIN_VALUE,MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is2xxSuccessful());

        verify(toDoListServiceImplMock,times(1)).updateToDoItem(any(ToDoItem.class));
    }

    @Test
    public void update_ToDoItem_fails_if_ids_donr_match() throws Exception {
        String updateToDoItemUri = "/todo/1";

        ObjectMapper o = new ObjectMapper();
        ObjectNode json = o.createObjectNode();
        json.put("priority", "1");
        json.put("status", "1");
        json.put("id", "2");
        String toDoItemJson = o.writeValueAsString(json);

        when(toDoListServiceImplMock.updateToDoItem(any(ToDoItem.class))).thenReturn(1L);
        mockMvc.perform(post(updateToDoItemUri)
                .content(toDoItemJson)
                .accept(MediaType.TEXT_PLAIN_VALUE,MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is4xxClientError());
        verify(toDoListServiceImplMock,times(0)).updateToDoItem(any(ToDoItem.class));
    }

    @Test
    public void can_list_all_todoitems() throws Exception {
        String getAllToDoItemsUri = "/todo/list";
        when(toDoListServiceImplMock.getAllToDoItems()).thenReturn(toDoItemsListMock);
        when(objectMapperMock.writeValueAsString(toDoItemsListMock)).thenReturn(dummyJson);
        mockMvc.perform(get(getAllToDoItemsUri)
                .accept(MediaType.TEXT_PLAIN_VALUE,MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is2xxSuccessful());
        verify(toDoListServiceImplMock,times(1)).getAllToDoItems();
    }

    @Test
    public void can_filter_all_todoitems_by_status() throws Exception {
        String getToDoItemsFilteredByStatusUri = "/todo/list/status/1";
        when(toDoListServiceImplMock.filterToDoItemsByStatus(1)).thenReturn(toDoItemsListMock);
        when(objectMapperMock.writeValueAsString(toDoItemsListMock)).thenReturn(dummyJson);
        mockMvc.perform(get(getToDoItemsFilteredByStatusUri)
                .accept(MediaType.TEXT_PLAIN_VALUE,MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is2xxSuccessful());
        verify(toDoListServiceImplMock,times(1)).filterToDoItemsByStatus(1);
    }

    @Test
    public void can_filter_all_todoitems_by_priority() throws Exception {
        String getToDoItemsFilteredByPriorityUri = "/todo/list/priority/1";
        when(toDoListServiceImplMock.filterToDoItemsByPriority(1)).thenReturn(toDoItemsListMock);
        when(objectMapperMock.writeValueAsString(toDoItemsListMock)).thenReturn(dummyJson);
        mockMvc.perform(get(getToDoItemsFilteredByPriorityUri)
                .accept(MediaType.TEXT_PLAIN_VALUE,MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is2xxSuccessful());
        verify(toDoListServiceImplMock,times(1)).filterToDoItemsByPriority(1);
    }

    @Test
    public void can_get_a_todoitem() throws Exception {
        String getToDoItemUri = "/todo/1";
        when(toDoListServiceImplMock.getToDOItem(1L)).thenReturn(toDoItemMock);
        when(objectMapperMock.writeValueAsString(toDoItemsListMock)).thenReturn(dummyJson);
        mockMvc.perform(get(getToDoItemUri)
                .accept(MediaType.TEXT_PLAIN_VALUE,MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is2xxSuccessful());
        verify(toDoListServiceImplMock,times(1)).getToDOItem(1L);
    }
}