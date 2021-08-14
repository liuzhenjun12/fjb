import request from '@/utils/request'

// 查询评论数量列表
export function listNum(query) {
  return request({
    url: '/system/num/list',
    method: 'get',
    params: query
  })
}

// 查询评论数量详细
export function getNum(id) {
  return request({
    url: '/system/num/' + id,
    method: 'get'
  })
}

// 新增评论数量
export function addNum(data) {
  return request({
    url: '/system/num',
    method: 'post',
    data: data
  })
}

// 修改评论数量
export function updateNum(data) {
  return request({
    url: '/system/num',
    method: 'put',
    data: data
  })
}

// 删除评论数量
export function delNum(id) {
  return request({
    url: '/system/num/' + id,
    method: 'delete'
  })
}

// 导出评论数量
export function exportNum(query) {
  return request({
    url: '/system/num/export',
    method: 'get',
    params: query
  })
}