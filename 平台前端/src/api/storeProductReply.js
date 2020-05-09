import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'api/storeProductReply',
    method: 'post',
    data
  })
}

export function del(id) {
  return request({
    url: 'api/storeProductReply/' + id,
    method: 'delete'
  })
}

export function edit(data) {
  return request({
    url: 'api/storeProductReply',
    method: 'put',
    data
  })
}
