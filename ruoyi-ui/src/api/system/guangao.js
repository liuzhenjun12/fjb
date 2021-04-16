import request from '@/utils/request'

// 查询网站评论列表
export function listGuangao(query) {
  return request({
    url: '/system/guangao/list',
    method: 'get',
    params: query
  })
}

// 查询网站评论详细
export function getGuangao(id) {
  return request({
    url: '/system/guangao/' + id,
    method: 'get'
  })
}

// 新增网站评论
export function addGuangao(data) {
  return request({
    url: '/system/guangao',
    method: 'post',
    data: data
  })
}

// 修改网站评论
export function updateGuangao(data) {
  return request({
    url: '/system/guangao',
    method: 'put',
    data: data
  })
}

// 删除网站评论
export function delGuangao(id) {
  return request({
    url: '/system/guangao/' + id,
    method: 'delete'
  })
}

// 导出网站评论
export function exportGuangao(query) {
  return request({
    url: '/system/guangao/export',
    method: 'get',
    params: query
  })
}