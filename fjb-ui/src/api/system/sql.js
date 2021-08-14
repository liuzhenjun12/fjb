import request from '@/utils/request'

// 下载报名表导入模板
export function importTemplate() {
  return request({
    url: '/system/sql/importTemplate',
    method: 'get'
  })
}


