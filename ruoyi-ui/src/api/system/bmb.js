import request from '@/utils/request'

// 查询报名列表
export function listBmb(query) {
  return request({
    url: '/system/bmb/list',
    method: 'get',
    params: query
  })
}

// 查询报名详细
export function getBmb(id) {
  return request({
    url: '/system/bmb/' + id,
    method: 'get'
  })
}

// 新增报名
export function addBmb(data) {
  return request({
    url: '/system/bmb',
    method: 'post',
    data: data
  })
}

// 修改报名
export function updateBmb(data) {
  return request({
    url: '/system/bmb',
    method: 'put',
    data: data
  })
}

// 删除报名
export function delBmb(id) {
  return request({
    url: '/system/bmb/' + id,
    method: 'delete'
  })
}

// 导出报名
export function exportBmb(query) {
  return request({
    url: '/system/bmb/export',
    method: 'get',
    params: query
  })
}

// 下载报名表导入模板
export function importTemplate() {
  return request({
    url: '/system/bmb/importTemplate',
    method: 'get'
  })
}

// 自动录入
export function luruBmb(id) {
  return request({
    url: '/system/bmb/luru/'+id,
    method: 'get'
  })
}

// 修改照片
export function updateAvatar(id) {
  return request({
    url: '/system/bmb/updateAvatar/'+id,
    method: 'get'
  })
}

// 是否完成状态修改
export function changeSfwc(id, sfwc) {
  const data = {
    id,
    sfwc
  }
  return request({
    url: '/system/bmb/changeSfwc',
    method: 'put',
    data: data
  })
}

// 是否补考状态修改
export function changeBukao(id, bukao) {
  const data = {
    id,
    bukao
  }
  return request({
    url: '/system/bmb/changeBukao',
    method: 'put',
    data: data
  })
}

// 导出统计报表
export function exportCount(data) {
  return request({
    url: '/system/bmb/exportCount?pici='+data,
    method: 'get'
  })
}
