package com.must.project.system.controller;

import com.must.project.system.domain.TodoList;
import com.must.project.system.service.ITodoListService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

class TodoListControllerTest extends BaseTest {
    @MockBean
    private ITodoListService mockTodoListService;

    @Test
    void testList() throws Exception {
        // Setup
        // Configure ITodoListService.selectTodoListList(...).
        final TodoList todoList = new TodoList();
        todoList.setUserId(0L);
        todoList.setTodoTitle("todoTitle");
        todoList.setTodoContent("todoContent");
        todoList.setDelFlag("delFlag");
        final List<TodoList> todoLists = Arrays.asList(todoList);
        when(mockTodoListService.selectTodoListList(any(TodoList.class))).thenReturn(todoLists);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/system/todolist/list")
                .headers(getHeaders())
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testList_ITodoListServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockTodoListService.selectTodoListList(any(TodoList.class))).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/system/todolist/list")
                .header("Authorization", getToken())
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetInfo() throws Exception {
        // Setup
        // Configure ITodoListService.selectTodoListByTodoId(...).
        final TodoList todoList = new TodoList();
        todoList.setTodoId(0L);
        todoList.setUserId(0L);
        todoList.setTodoTitle("todoTitle");
        todoList.setTodoContent("todoContent");
        todoList.setDelFlag("delFlag");
        when(mockTodoListService.selectTodoListByTodoId(0L)).thenReturn(todoList);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/system/todolist/{todoId}", 0)
                .header("Authorization", getToken())
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testAdd() throws Exception {
        // Setup
        when(mockTodoListService.insertTodoList(any(TodoList.class))).thenReturn(0);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/system/todolist")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", getToken())
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testEdit() throws Exception {
        // Setup
        when(mockTodoListService.updateTodoList(any(TodoList.class))).thenReturn(0);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put("/system/todolist")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", getToken())
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testRemove() throws Exception {
        // Setup
        when(mockTodoListService.deleteTodoListByTodoIds(any(Long[].class))).thenReturn(0);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/system/todolist/{todoIds}", 0)
                .header("Authorization", getToken())
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
