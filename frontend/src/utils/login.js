import { post } from '@/utils/http.js'

// // 登录接口
// export const registerLogin = async function (param) {
//   const res = await get('user/login', param)
//   return res
// }
// 登录接口
export const registerLogin = async function (param) {
  const res = await post('user/login', param)
  return res
}
// 用户注册接口
export const registerUser = async function (param) {
  const res = await post('register/user', param)
  return res
}
// 商家
export const registerShop = async function (param) {
  const res = await post('register/shop', param)
  return res
}
