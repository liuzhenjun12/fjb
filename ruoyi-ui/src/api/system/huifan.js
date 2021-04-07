import request from '@/utils/request'

// 查询回访记录列表
export function listHuifan(query) {
  return request({
    url: '/system/huifan/list',
    method: 'get',
    params: query
  })
}

// 查询回访记录详细
export function getHuifan(id) {
  return request({
    url: '/system/huifan/' + id,
    method: 'get'
  })
}

// 新增回访记录
export function addHuifan(data) {
  return request({
    url: '/system/huifan',
    method: 'post',
    data: data
  })
}

// 修改回访记录
export function updateHuifan(data) {
  return request({
    url: '/system/huifan',
    method: 'put',
    data: data
  })
}

// 删除回访记录
export function delHuifan(id) {
  return request({
    url: '/system/huifan/' + id,
    method: 'delete'
  })
}

// 导出回访记录
export function exportHuifan(query) {
  return request({
    url: '/system/huifan/export',
    method: 'get',
    params: query
  })
}