import axios from 'axios'

class SWANRequest {
  instance
  interceptors
  showLoading
  loading

  constructor(config) {
    this.instance = axios.create(config)
    this.interceptors = config.interceptors

    //实例请求拦截
    this.instance.interceptors.request.use(
      this.interceptors?.requestInterceptor,
      this.interceptors?.requestInterceptorCatch
    )

    //实例响应拦截
    this.instance.interceptors.response.use(
      this.interceptors?.responseInterceptor,
      this.interceptors?.responseInterceptorCatch
    )

    // 添加所有的实例都有的拦截器
    this.instance.interceptors.request.use(
      (config) => {

        return config
      },
      (err) => {
        return err
      }
    )

    //全局响应拦截器
    this.instance.interceptors.response.use(
      (res) => {
        const data = res.data
        if (data?.returnCode === '-1001') {
          console.log('请求失败~, 错误信息')
        } else {
          return data
        }
      },
      (err) => {

        return err
      }
    )
  }

  request(config) {
    return new Promise((resolve, reject) => {
      // 1.单个请求对请求config的处理
      if (config.interceptors?.requestInterceptor) {
        config = config.interceptors.requestInterceptor(config)
      }

      this.instance
        .request(config)
        .then((res) => {
          // 1.单个请求对数据的处理
          if (config.interceptors?.responseInterceptor) {
            res = config.interceptors.responseInterceptor(res)
          }

          // 3.将结果resolve返回出去
          resolve(res)
        })
        .catch((err) => {
          reject(err)
          return err
        })
    })
  }

  get (config) {
    return this.request({...config, method: 'GET'})
  }

  post (config) {
    return this.request({...config, method: 'POST'})
  }
}

export default SWANRequest