package com.must.project.system.controller;

import com.must.framework.aspectj.lang.annotation.Log;
import com.must.framework.aspectj.lang.enums.BusinessType;
import com.must.framework.web.controller.BaseController;
import com.must.framework.web.domain.AjaxResult;
import com.must.framework.web.page.TableDataInfo;
import com.must.project.system.domain.TodoList;
import com.must.project.system.service.ITodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 待办清单Controller
 * 
 * @author zhancai
 * @date 2022-05-13
 */
@RestController
@RequestMapping("/system/todolist")
public class TodoListController extends BaseController
{
    @Autowired
    private ITodoListService todoListService;

    /**
     * 查询待办清单列表
     */
    @PreAuthorize("@ss.hasPermi('system:todolist:list')")
    @GetMapping("/list")
    public TableDataInfo list(TodoList todoList)
    {
        startPage();
        List<TodoList> list = todoListService.selectTodoListList(todoList);
        return getDataTable(list);
    }

    /**
     * 获取待办清单详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:todolist:query')")
    @GetMapping(value = "/{todoId}")
    public AjaxResult getInfo(@PathVariable("todoId") Long todoId)
    {
        return AjaxResult.success(todoListService.selectTodoListByTodoId(todoId));
    }

    /**
     * 新增待办清单
     */
    @PreAuthorize("@ss.hasPermi('system:todolist:add')")
    @Log(title = "待办清单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TodoList todoList)
    {
        return toAjax(todoListService.insertTodoList(todoList));
    }

    /**
     * 修改待办清单
     */
    @PreAuthorize("@ss.hasPermi('system:todolist:edit')")
    @Log(title = "待办清单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TodoList todoList)
    {
        return toAjax(todoListService.updateTodoList(todoList));
    }

    /**
     * 删除待办清单
     */
    @PreAuthorize("@ss.hasPermi('system:todolist:remove')")
    @Log(title = "待办清单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{todoIds}")
    public AjaxResult remove(@PathVariable Long[] todoIds)
    {
        return toAjax(todoListService.deleteTodoListByTodoIds(todoIds));
    }
}
