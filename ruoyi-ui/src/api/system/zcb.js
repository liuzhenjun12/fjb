import request from '@/utils/request'

// 查询注册列表
export function listZcb(query) {
  return request({
    url: '/system/zcb/list',
    method: 'get',
    params: query
  })
}

// 查询注册详细
export function getZcb(id) {
  return request({
    url: '/system/zcb/' + id,
    method: 'get'
  })
}

// 新增注册
export function addZcb(data) {
  return request({
    url: '/system/zcb',
    method: 'post',
    data: data
  })
}

// 修改注册
export function updateZcb(data) {
  return request({
    url: '/system/zcb',
    method: 'put',
    data: data
  })
}

// 删除注册
export function delZcb(id) {
  return request({
    url: '/system/zcb/' + id,
    method: 'delete'
  })
}

// 导出注册
export function exportZcb(query) {
  return request({
    url: '/system/zcb/export',
    method: 'get',
    params: query
  })
}

// 下载注册表导入模板
export function importTemplate() {
  return request({
    url: '/system/zcb/importTemplate',
    method: 'get'
  })
}
