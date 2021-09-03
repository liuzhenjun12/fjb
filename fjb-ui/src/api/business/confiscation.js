import request from '@/utils/request'

// 查询没收凭证列表
export function listConfiscation(query) {
  return request({
    url: '/business/confiscation/list',
    method: 'get',
    params: query
  })
}

// 查询没收凭证详细
export function getConfiscation(id) {
  return request({
    url: '/business/confiscation/' + id,
    method: 'get'
  })
}

// 新增没收凭证
export function addConfiscation(data) {
  return request({
    url: '/business/confiscation',
    method: 'post',
    data: data
  })
}

// 修改没收凭证
export function updateConfiscation(data) {
  return request({
    url: '/business/confiscation',
    method: 'put',
    data: data
  })
}

// 删除没收凭证
export function delConfiscation(id) {
  return request({
    url: '/business/confiscation/' + id,
    method: 'delete'
  })
}

// 导出没收凭证
export function exportConfiscation(query) {
  return request({
    url: '/business/confiscation/export',
    method: 'get',
    params: query
  })
}
