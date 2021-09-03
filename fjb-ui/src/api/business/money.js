import request from '@/utils/request'

// 查询货币标准列表
export function listMoney(query) {
  return request({
    url: '/business/money/list',
    method: 'get',
    params: query
  })
}

// 查询货币标准详细
export function getMoney(id) {
  return request({
    url: '/business/money/' + id,
    method: 'get'
  })
}

// 新增货币标准
export function addMoney(data) {
  return request({
    url: '/business/money',
    method: 'post',
    data: data
  })
}

// 修改货币标准
export function updateMoney(data) {
  return request({
    url: '/business/money',
    method: 'put',
    data: data
  })
}

// 删除货币标准
export function delMoney(id) {
  return request({
    url: '/business/money/' + id,
    method: 'delete'
  })
}

// 导出货币标准
export function exportMoney(query) {
  return request({
    url: '/business/money/export',
    method: 'get',
    params: query
  })
}
