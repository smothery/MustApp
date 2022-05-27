import request from '@/utils/request'

// 查询待办清单列表
export function listTodolist(query) {
  return request({
    url: '/system/todolist/list',
    method: 'get',
    params: query
  })
}

// 查询待办清单详细
export function getTodolist(todoId) {
  return request({
    url: '/system/todolist/' + todoId,
    method: 'get'
  })
}

// 新增待办清单
export function addTodolist(data) {
  return request({
    url: '/system/todolist',
    method: 'post',
    data: data
  })
}

// 修改待办清单
export function updateTodolist(data) {
  return request({
    url: '/system/todolist',
    method: 'put',
    data: data
  })
}

// 删除待办清单
export function delTodolist(todoId) {
  return request({
    url: '/system/todolist/' + todoId,
    method: 'delete'
  })
}
