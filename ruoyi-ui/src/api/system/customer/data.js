import request from '@/utils/request'

// 查询回访记录列表
export function listHuifan(query) {
  return request({
    url: '/system/customer/data/list',
    method: 'get',
    params: query
  })
}

// 查询回访记录详细
export function getHuifan(id) {
  return request({
    url: '/system/customer/data/' + id,
    method: 'get'
  })
}
// 根据字典类型查询字典数据信息
export function getDicts(dictType) {
  return request({
    url: '/system/customer/data/type/' + dictType,
    method: 'get'
  })
}

// 新增回访记录
export function addHuifan(data) {
  return request({
    url: '/system/customer/data',
    method: 'post',
    data: data
  })
}

// 修改回访记录
export function updateHuifan(data) {
  return request({
    url: '/system/customer/data',
    method: 'put',
    data: data
  })
}

// 删除回访记录
export function delHuifan(id) {
  return request({
    url: '/system/customer/data/' + id,
    method: 'delete'
  })
}

// 导出回访记录
export function exportHuifan(query) {
  return request({
    url: '/system/customer/export',
    method: 'get',
    params: query
  })
}
