package com.must.project.system.service;

import com.must.project.system.domain.TodoList;

import java.util.List;

/**
 * 待办清单Service接口
 * 
 * @author zhancai
 * @date 2022-05-13
 */
public interface ITodoListService 
{
    /**
     * 查询待办清单
     * 
     * @param todoId 待办清单主键
     * @return 待办清单
     */
    public TodoList selectTodoListByTodoId(Long todoId);

    /**
     * 查询待办清单列表
     * 
     * @param todoList 待办清单
     * @return 待办清单集合
     */
    public List<TodoList> selectTodoListList(TodoList todoList);

    /**
     * 新增待办清单
     * 
     * @param todoList 待办清单
     * @return 结果
     */
    public int insertTodoList(TodoList todoList);

    /**
     * 修改待办清单
     * 
     * @param todoList 待办清单
     * @return 结果
     */
    public int updateTodoList(TodoList todoList);

    /**
     * 批量删除待办清单
     * 
     * @param todoIds 需要删除的待办清单主键集合
     * @return 结果
     */
    public int deleteTodoListByTodoIds(Long[] todoIds);

    /**
     * 删除待办清单信息
     * 
     * @param todoId 待办清单主键
     * @return 结果
     */
    public int deleteTodoListByTodoId(Long todoId);
}
