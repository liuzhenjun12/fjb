import request from '@/utils/request'

// 查询全部部门列表
export function listAll() {
  return request({
    url: '/system/dept/listAll',
    method: 'get'
  })
}
// 查询上级部门列表
export function listDept(query) {
  return request({
    url: '/system/dept/list',
    method: 'get',
    params: query
  })
}
// 查询下级部门列表
export function nextDept(deptId) {
  return request({
    url: '/system/dept/next/' + deptId,
    method: 'get'
  })
}


// 查询部门列表（排除节点）
export function listDeptExcludeChild(deptId) {
  return request({
    url: '/system/dept/list/exclude/' + deptId,
    method: 'get'
  })
}

// 查询部门详细
export function getDept(deptId) {
  return request({
    url: '/system/dept/' + deptId,
    method: 'get'
  })
}

// 查询部门下拉树结构
export function treeselect() {
  return request({
    url: '/system/dept/treeselect',
    method: 'get'
  })
}

// 查询部门级联下拉结构
export function Cascadeselect(query) {
  return request({
    url: '/system/dept/cascadeselect',
    method: 'get',
    params: query
  })
}

// 根据角色ID查询部门树结构
export function roleDeptTreeselect(roleId) {
  return request({
    url: '/system/dept/roleDeptTreeselect/' + roleId,
    method: 'get'
  })
}

// 新增部门
export function addDept(data) {
  return request({
    url: '/system/dept',
    method: 'post',
    data: data
  })
}

// 修改部门
export function updateDept(data) {
  return request({
    url: '/system/dept',
    method: 'put',
    data: data
  })
}

// 删除部门
export function delDept(deptId) {
  return request({
    url: '/system/dept/' + deptId,
    method: 'delete'
  })
}

// 查询省列表
export function listProvince(query) {
  return request({
    url: '/system/dept/province/list',
    method: 'get',
    params: query
  })
}

// 查询市列表
export function listCity(query) {
  return request({
    url: '/system/dept/city/list',
    method: 'get',
    params: query
  })
}

// 查询县列表
export function listTown(query) {
  return request({
    url: '/system/dept/town/list',
    method: 'get',
    params: query
  })
}
