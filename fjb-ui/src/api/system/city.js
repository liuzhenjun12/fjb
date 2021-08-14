import request from '@/utils/request'

// 查询城市名称列表
export function listCity(query) {
  return request({
    url: '/system/city/list',
    method: 'get',
    params: query
  })
}

// 查询城市名称详细
export function getCity(id) {
  return request({
    url: '/system/city/' + id,
    method: 'get'
  })
}

// 新增城市名称
export function addCity(data) {
  return request({
    url: '/system/city',
    method: 'post',
    data: data
  })
}

// 修改城市名称
export function updateCity(data) {
  return request({
    url: '/system/city',
    method: 'put',
    data: data
  })
}

// 删除城市名称
export function delCity(id) {
  return request({
    url: '/system/city/' + id,
    method: 'delete'
  })
}

// 导出城市名称
export function exportCity(query) {
  return request({
    url: '/system/city/export',
    method: 'get',
    params: query
  })
}