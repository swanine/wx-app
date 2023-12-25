import Taro from "@tarojs/taro";

class SWANRequest {
  request (path, method, params){
    return new Promise(( resolve, reject ) => {

      Taro.cloud.callContainer({
        config: {
          env: 'prod-7g1l0ifpbf7df815', // 微信云托管的环境ID
        },
        path, // 填入业务自定义路径和参数，根目录，就是 / 
        method, // 按照自己的业务开发，选择对应的方法
        header: {
          'X-WX-SERVICE': 'springboot-uj47', // xxx中填入服务名称（微信云托管 - 服务管理 - 服务列表 - 服务名称）
        },
        data: params,
        success: function(res) {
          resolve(res.data)
        },
        fail: reject
        // dataType:'text', // 默认不填是以JSON形式解析返回结果，若不想让SDK自己解析，可以填text
        // 其余参数同 wx.request
      })
    })
  }

  get({url, params}) {
    return this.request(url, "GET", params)
  }

  post({url, data}) {
    return this.request(url, "POST", data)
  }
}

const swanRequest = new SWANRequest()

export default swanRequest