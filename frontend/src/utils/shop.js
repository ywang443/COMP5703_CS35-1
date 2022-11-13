import { post, get } from '@/utils/http.js'

// 获取商家首页信息
export const getShopHomeInfo = async function (param) {
  const res = await get('shop/home')
  return res
}

// 获取商家订单列表
export const postShopOrder = async function (param) {
  const res = await post('shop/order', param)
  return res
}
// 更改桌子状态（手动控制桌子是否可用）
export const postTableStatus = async function (param) {
  const res = await post('shop/table', param)
  return res
}
// 获取商家邮件信息
export const postShopMail = async function (param) {
  const res = await post('shop/mail', param)
  return res
}
// 更改邮件状态（已读未读）
export const postMailStatus = async function (param) {
  const res = await post('shop/mail/status', param)
  return res
}
// 获取用户预定详细信息
export const postShopOrderInfo = async function (param) {
  const res = await post('shop/order/info', param)
  return res
}
