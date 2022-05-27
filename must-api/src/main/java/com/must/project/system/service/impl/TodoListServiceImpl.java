package com.must.project.system.service.impl;

import com.must.common.exception.ServiceException;
import com.must.common.utils.DateUtils;
import com.must.common.utils.SecurityUtils;
import com.must.project.system.domain.TodoList;
import com.must.project.system.mapper.TodoListMapper;
import com.must.project.system.service.ITodoListService;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 待办清单Service业务层处理
 * 
 * @author zhancai
 * @date 2022-05-13
 */
@Service
public class TodoListServiceImpl implements ITodoListService 
{
    @Autowired
    private TodoListMapper todoListMapper;

    /**
     * 查询待办清单
     * 
     * @param todoId 待办清单主键
     * @return 待办清单
     */
    @Override
    public TodoList selectTodoListByTodoId(Long todoId)
    {
        return todoListMapper.selectTodoListByTodoId(todoId);
    }

    /**
     * 查询待办清单列表
     * 
     * @param todoList 待办清单
     * @return 待办清单
     */
    @Override
    public List<TodoList> selectTodoListList(TodoList todoList)
    {
        if (!SecurityUtils.isAdmin())
        {
            todoList.setUserId(SecurityUtils.getUserId());
        }
        return todoListMapper.selectTodoListList(todoList);
    }

    /**
     * 新增待办清单
     * 
     * @param todoList 待办清单
     * @return 结果
     */
    @Override
    public int insertTodoList(TodoList todoList)
    {
        todoList.setUserId(SecurityUtils.getUserId());
        todoList.setCreateBy(SecurityUtils.getUsername());
        todoList.setCreateTime(DateUtils.getNowDate());
        return todoListMapper.insertTodoList(todoList);
    }

    /**
     * 修改待办清单
     * 
     * @param todoList 待办清单
     * @return 结果
     */
    @Override
    public int updateTodoList(TodoList todoList)
    {
        TodoList record = selectTodoListByTodoId(todoList.getTodoId());

        if (Objects.isNull(record))
        {
            return NumberUtils.INTEGER_ZERO;
        }

        if (!record.getUserId().equals(SecurityUtils.getUserId()))
        {
            throw new ServiceException("只能修改本人待办清单");
        }

        todoList.setUpdateBy(SecurityUtils.getUsername());
        todoList.setUpdateTime(DateUtils.getNowDate());
        return todoListMapper.updateTodoList(todoList);
    }

    /**
     * 批量删除待办清单
     * 
     * @param todoIds 需要删除的待办清单主键
     * @return 结果
     */
    @Override
    public int deleteTodoListByTodoIds(Long[] todoIds)
    {
        return todoListMapper.deleteTodoListByTodoIds(todoIds, SecurityUtils.getUsername());
    }

    /**
     * 删除待办清单信息
     * 
     * @param todoId 待办清单主键
     * @return 结果
     */
    @Override
    public int deleteTodoListByTodoId(Long todoId)
    {
        return todoListMapper.deleteTodoListByTodoId(todoId, SecurityUtils.getUsername());
    }
}
