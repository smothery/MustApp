package com.must.project.system.domain;

import com.must.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 待办清单对象 todo_list
 * 
 * @author zhancai
 * @date 2022-05-13
 */
public class TodoList extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 待办ID */
    private Long todoId;

    /** 用户ID */
    private Long userId;

    /** 待办标题 */
    private String todoTitle;

    /** 待办内容 */
    private String todoContent;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setTodoId(Long todoId)
    {
        this.todoId = todoId;
    }

    public Long getTodoId()
    {
        return todoId;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setTodoTitle(String todoTitle)
    {
        this.todoTitle = todoTitle;
    }

    public String getTodoTitle()
    {
        return todoTitle;
    }
    public void setTodoContent(String todoContent)
    {
        this.todoContent = todoContent;
    }

    public String getTodoContent()
    {
        return todoContent;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("todoId", getTodoId())
                .append("userId", getUserId())
                .append("todoTitle", getTodoTitle())
                .append("todoContent", getTodoContent())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .append("delFlag", getDelFlag())
                .toString();
    }
}
