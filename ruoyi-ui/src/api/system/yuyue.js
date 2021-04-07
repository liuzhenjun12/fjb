import request from '@/utils/request'

// 查询预约列表
export function listYuyue(query) {
  return request({
    url: '/system/yuyue/list',
    method: 'get',
    params: query
  })
}

// 查询预约详细
export function getYuyue(id) {
  return request({
    url: '/system/yuyue/' + id,
    method: 'get'
  })
}

// 新增预约
export function addYuyue(data) {
  return request({
    url: '/system/yuyue',
    method: 'post',
    data: data
  })
}

// 修改预约
export function updateYuyue(data) {
  return request({
    url: '/system/yuyue',
    method: 'put',
    data: data
  })
}

// 删除预约
export function delYuyue(id) {
  return request({
    url: '/system/yuyue/' + id,
    method: 'delete'
  })
}

// 导出预约
export function exportYuyue(query) {
  return request({
    url: '/system/yuyue/export',
    method: 'get',
    params: query
  })
}