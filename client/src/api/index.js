import request from '../utils'
// import request from '../utils/request/cloud'



// 获取评审列表
export function getEvaluateList(data) {
  return request.post({ url: '/wx/evaluate/getEvaluateList', data })
}

// 添加评审单信息
export function addEvaluate(data) {
  return request.post({
    url: '/wx/evaluate/addEvaluate',
    data
  })
}

// 添加评审单信息
export function feedback(data) {
  return request.post({
    url: '/wx/evaluate/feedback',
    data
  })
}


// 获取评审详情
export function getEvaluateDetail(id) {
  return request.post({
    url: `/wx/evaluate/getEvaluateDetail?id=${id}`,
  })
}

// // 获取评审详情 cloud
// export function getEvaluateDetail_C(id) {
//   return request.post(`/wx/evaluate/getEvaluateDetail?id=${id}`)
// }