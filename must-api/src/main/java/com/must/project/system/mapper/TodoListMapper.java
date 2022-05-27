package com.must.project.system.mapper;

import com.must.project.system.domain.TodoList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 待办清单Mapper接口
 * 
 * @author zhancai
 * @date 2022-05-13
 */
public interface TodoListMapper 
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
     * 删除待办清单
     *
     * @param todoId 待办清单主键
     * @return 结果
     */
    public int deleteTodoListByTodoId(@Param("todoId") Long todoId, @Param("updateBy") String updateBy);

    /**
     * 批量删除待办清单
     *
     * @param todoIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTodoListByTodoIds(@Param("todoIds") Long[] todoIds, @Param("updateBy") String updateBy);
}
