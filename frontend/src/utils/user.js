import { post, get } from '@/utils/http.js'

// 获取用户首页信息
export const getUserHomeInfo = async function (param) {
  const res = await get('user/home')
  return res
}

// 获取全部推荐排行榜餐厅数据
export const getAllRestaurantList = async function (param) {
  const res = await get('user/home/ranking')
  return res
}
// 获取餐厅详情
export const getRestaurantDetails = async function (param) {
  const res = await get('user/restaurant', param)
  return res
}
// 获取下单页餐厅信息
export const getBuyRestaurant = async function (param) {
  const res = await get('user/order/lower', param)
  return res
}
// 获取搜索页信息
export const getSearchInfo = async function (param) {
  const res = await get('user/search/info')
  return res
}
// 根据关键词搜索展示内容
export const getSearch = async function (param) {
  const res = await get('user/search', param)
  return res
}
// 提交订单
export const postOrderAdd = async function (param) {
  const res = await post('user/order/add', param)
  return res
}
// 更改餐厅收藏状态
export const postRestaurantStatus = async function (param) {
  const res = await post('user/search/popular', param)
  return res
}
// 获取用户订单列表
export const postUserOrder = async function (param) {
  const res = await post('user/order', param)
  return res
}
// 获取用户收藏列表
export const postUserLike = async function (param) {
  const res = await post('user/like', param)
  return res
}
// 获取用户邮件列表
export const postUserMail = async function (param) {
  const res = await post('user/mail', param)
  return res
}
// 获取邮件详细信息
export const postUserMailInfo = async function (param) {
  const res = await post('user/mail/info', param)
  return res
}
// 获取商家类别
export const getUserShopCategory = async function (param) {
  const res = await get('user/category', param)
  return res
}
