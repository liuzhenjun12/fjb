import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listProduct(query) {
  return request({
    url: '/system/product/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getProduct(id) {
  return request({
    url: '/system/product/' + id,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addProduct(data) {
  return request({
    url: '/system/product',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateProduct(data) {
  return request({
    url: '/system/product',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delProduct(id) {
  return request({
    url: '/system/product/' + id,
    method: 'delete'
  })
}

// 导出【请填写功能名称】
export function exportProduct(query) {
  return request({
    url: '/system/product/export',
    method: 'get',
    params: query
  })
}