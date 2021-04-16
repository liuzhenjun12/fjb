import request from '@/utils/request'

// 查询报名列表
export function listBmb(query) {
  return request({
    url: '/system/bmb/list',
    method: 'get',
    params: query
  })
}

// 查询统计报名列表
export function countList(query) {
  return request({
    url: '/system/bmb/countList',
    method: 'get',
    params: query
  })
}

// 用于首页地图数据
export function findShengCount() {
  return request({
    url: '/system/bmb/findShengCount',
    method: 'get',
  })
}

// 首页统计订单地图
export function countOrder() {
  return request({
    url: '/system/bmb/countOrder',
    method: 'get',
  })
}

// 首页统计订单线性图
export function countOrderXian(nian) {
  return request({
    url: '/system/bmb/countOrderXian/'+nian,
    method: 'get',
  })
}

// 首页统计反假测评人数线性图
export function countBaoXian(nian) {
  return request({
    url: '/system/bmb/countBaoXian/'+nian,
    method: 'get',
  })
}

// 首页统计回访客户线性图
export function countHuifanXian(nian) {
  return request({
    url: '/system/bmb/countHuifanXian/'+nian,
    method: 'get',
  })
}

// 首页统计客户行业图
export function countQy() {
  return request({
    url: '/system/bmb/countQy',
    method: 'get',
  })
}

// 各省报名人数
export function countSheng() {
  return request({
    url: '/system/bmb/countSheng',
    method: 'get',
  })
}

// 购买产品最多的客户
export function countMai() {
  return request({
    url: '/system/bmb/countMai',
    method: 'get',
  })
}

// 用于首页头部面板数据
export function countAll() {
  return request({
    url: '/system/bmb/countAll',
    method: 'get',
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

// 从测评中心下载成绩
export function downloadChengji(pici,deptId) {
  return request({
    url: '/system/bmb/downloadChengji?pici='+pici+'&deptId='+deptId,
    method: 'get'
  })
}
