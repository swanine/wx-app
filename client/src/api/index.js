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

// 工作日报相关

// 工作日报列表
export function getDailyReportList(data) {
  return request.post({ url: '/wx/daily/getDailyReportList', data })
}

// 新增或更新
export function addOrUpdateReport(data) {
  return request.post({
    url: '/wx/daily/addOrUpdateReport',
    data
  })
}

// 工作日报详情
export function getDailyReportDetail(id) {
  return request.post({
    url: `/wx/daily/getDailyReportDetail?id=${id}`,
  })
}

// 登录
export function wxLogin(code) {
  return request.post({
    url: `/wx/login?code=${code}`,
  })
}

// 手机号
export function getUserPhone(code) {
  return request.post({
    url: `/wx/getUserPhone?code=${code}`,
  })
}
