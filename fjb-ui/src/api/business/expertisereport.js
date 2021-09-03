import request from '@/utils/request'

// 查询假币鉴定列表
export function listExpertisereport(query) {
  return request({
    url: '/business/expertisereport/list',
    method: 'get',
    params: query
  })
}

// 查询假币鉴定详细
export function getExpertisereport(id) {
  return request({
    url: '/business/expertisereport/' + id,
    method: 'get'
  })
}

// 新增假币鉴定
export function addExpertisereport(data) {
  return request({
    url: '/business/expertisereport',
    method: 'post',
    data: data
  })
}

// 修改假币鉴定
export function updateExpertisereport(data) {
  return request({
    url: '/business/expertisereport',
    method: 'put',
    data: data
  })
}

// 删除假币鉴定
export function delExpertisereport(id) {
  return request({
    url: '/business/expertisereport/' + id,
    method: 'delete'
  })
}

// 导出假币鉴定
export function exportExpertisereport(query) {
  return request({
    url: '/business/expertisereport/export',
    method: 'get',
    params: query
  })
}
// 假币鉴定状态修改
export function changeExpertisereportStatus(id, status) {
  const data = {
    id,
    status
  }
  return request({
    url: '/business/expertisereport/changeStatus',
    method: 'put',
    data: data
  })
}
// 收缴凭证获取子节点
export function getParentNum(id) {
  return request({
    url: '/business/expertisereport/parentNum/' + id,
    method: 'get'
  })
}
