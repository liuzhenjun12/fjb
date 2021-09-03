import request from '@/utils/request'

// 查询单选列表
export function listAn(query) {
  return request({
    url: '/fake/tiku/an/list',
    method: 'get',
    params: query
  })
}

// 查询单选详细
export function getAn(id) {
  return request({
    url: '/fake/tiku/an/' + id,
    method: 'get'
  })
}

// 新增单选
export function addAn(data) {
  return request({
    url: '/fake/tiku/an',
    method: 'post',
    data: data
  })
}

// 修改单选
export function updateAn(data) {
  return request({
    url: '/fake/tiku/an',
    method: 'put',
    data: data
  })
}

// 删除单选
export function delAn(id) {
  return request({
    url: '/fake/tiku/an/' + id,
    method: 'delete'
  })
}
