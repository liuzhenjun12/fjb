import request from '@/utils/request'

// 查询单选列表
export function listDanxuan(query) {
  return request({
    url: '/fake/tiku/list',
    method: 'get',
    params: query
  })
}

// 查询单选详细
export function getDanxuan(id) {
  return request({
    url: '/fake/tiku/' + id,
    method: 'get'
  })
}

// 新增单选
export function addDanxuan(data) {
  return request({
    url: '/fake/tiku',
    method: 'post',
    data: data
  })
}

// 修改单选
export function updateDanxuan(data) {
  return request({
    url: '/fake/tiku',
    method: 'put',
    data: data
  })
}

// 删除单选
export function delDanxuan(id) {
  return request({
    url: '/fake/tiku/' + id,
    method: 'delete'
  })
}

// 导出单选
export function exportDanxuan(query) {
  return request({
    url: '/fake/tiku/export',
    method: 'get',
    params: query
  })
}

// 状态修改
export function changeDanxuanStatus(id, status) {
  const data = {
    id,
    status
  }
  return request({
    url: '/fake/tiku/changeStatus',
    method: 'put',
    data: data
  })
}
