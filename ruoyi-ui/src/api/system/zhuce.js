import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listZhuce(query) {
  return request({
    url: '/system/zhuce/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getZhuce(zcId) {
  return request({
    url: '/system/zhuce/' + zcId,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addZhuce(data) {
  return request({
    url: '/system/zhuce',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateZhuce(data) {
  return request({
    url: '/system/zhuce',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delZhuce(zcId) {
  return request({
    url: '/system/zhuce/' + zcId,
    method: 'delete'
  })
}

// 导出【请填写功能名称】
export function exportZhuce(query) {
  return request({
    url: '/system/zhuce/export',
    method: 'get',
    params: query
  })
}

// 导入注册表
export function importZhuce(data) {
  return request({
    url: '/system/zhuce/import',
    method: 'get',
    params: data
  })
}
// 审核【请填写功能名称】
export function appver(data) {
  return request({
    url: '/system/zhuce/appver',
    method: 'post',
    params: data
  })
}

  // 核对【请填写功能名称】
  export function hedui(data) {
    return request({
      url: '/system/zhuce/hedui',
      method: 'post',
      params: data
    })
}
