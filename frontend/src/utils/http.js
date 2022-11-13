/**
 * 配置axios的超时、请求拦截器、响应拦截器等等
 */

import axios from 'axios'
import { Toast } from 'vant'
import store from '../store/index'
import router from '@/router'
import QS from 'qs' // 引入qs模块，用来序列化post类型的数据

// 设置请求超时
axios.defaults.timeout = 10000

// post请求头的设置
axios.defaults.headers.post['Content-Type'] =
  'application/x-www-form-urlencoded;charset=UTF-8'

axios.defaults.headers.get['Content-Type'] = 'application/json'

const baseUrl = 'http://43.140.198.220:8958/' // 服务器1
// ''http://43.140.198.220:7777/upload/' + item.img' // 图片默认地址
// const baseUrl = 'http://43.140.198.220:7777/' // 服务器2
// const baseUrl = 'http://114.132.72.197:8958/' // 服务器1
// const baseUrl = 'https://jobs.neoweb.top/' // 服务器1
// const baseUrl = 'http://192.168.110.220:8080/' // 本地测试

// 请求拦截器
axios.interceptors.request.use(
  config => {
    // 每次发送请求之前判断是否存在token，如果存在，则统一在http请求的header都加上token，不用每次请求都手动添加了
    // 即使本地存在token，也有可能token是过期的，所以在响应拦截器中要对返回状态进行判断
    // const token = store.state.token
    // token && (config.headers.Authorization = token)
    // console.log(token, config)
    return config
  },
  error => {
    // console.log(error)
    return Promise.error(error)
  }
)

// 响应拦截器
axios.interceptors.response.use(
  response => {
    // console.log(response, 123)
    if (response.status === 200) {
      if (response.data.code === 110) {
        router.push({
          path: '/'
        })
        // console.log(456)
        Toast('请重新登录')
        return
      }
      return Promise.resolve(response)
    } else {
      return Promise.reject(response)
    }
  },
  // 服务器状态码不是200的情况
  error => {
    console.log(789, error)
    if (error?.response?.status) {
      switch (error.response.status) {
        // 401: 未登录
        // 未登录则跳转登录页面，并携带当前页面的路径
        // 在登录成功后返回当前页面，这一步需要在登录页操作。
        case 401:
          this.$router.replace({
            path: '/',
            query: { redirect: this.$router.currentRoute.fullPath }
          })
          break
        // 403 token过期
        // 登录过期对用户进行提示
        // 清除本地token和清空vuex中token对象
        // 跳转登录页面
        case 403:
          Toast({
            message: '登录过期，请重新登录',
            duration: 1000,
            forbidClick: true
          })
          // 清除token
          localStorage.removeItem('token')
          store.commit('loginSuccess', null)
          // 跳转登录页面，并将要浏览的页面fullPath传过去，登录成功后跳转需要访问的页面
          setTimeout(() => {
            this.$router.replace({
              path: '/',
              query: {
                redirect: this.$router.currentRoute.fullPath
              }
            })
          }, 1000)
          break
        // 404请求不存在
        case 404:
          Toast({
            message: '网络请求不存在',
            duration: 1500,
            forbidClick: true
          })
          break
        // 其他错误，直接抛出错误提示
        default:
          Toast({
            message: error.response.data.message,
            duration: 1500,
            forbidClick: true
          })
      }
      return Promise.reject(error.response)
    }
  }
)

/**
 * get方法，对应get请求
 * @param {String} url [请求的url地址]
 * @param {Object} params [请求时携带的参数]
 */
export function get(url, params) {
  return new Promise((resolve, reject) => {
    axios({
      method: 'GET',
      url: params ? `${baseUrl}${url}?${params}` : `${baseUrl}${url}`
      // data: params
    })
      .then(res => {
        resolve(res)
      })
      .catch(err => {
        reject(err)
      })
  })
}

/**
 * post方法，对应post请求
 * @param {String} url [请求的url地址]
 * @param {Object} params [请求时携带的参数]
 */
export function post(url, params) {
  // console.log(localStorage.getItem('token'))
  // 在post请求里面添加token   // 如果全局状态管理里面没有token，就从本地缓存中拿
  const token = localStorage.getItem('token')

  params = { ...params, token: store.state.token || token }
  return new Promise((resolve, reject) => {
    axios({
      method: 'POST',
      url: baseUrl + url,
      data: QS.stringify(params)
    })
      .then(res => {
        resolve(res)
      })
      .catch(err => {
        reject(err)
      })
  })
}
