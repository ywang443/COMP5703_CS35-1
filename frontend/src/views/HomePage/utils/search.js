import { getSearch } from '@/utils/user'
import { Toast } from 'vant'

export const searchInfo = function (f) {
  const value = `value=${f}`
  getSearch(value).then(res => {
    console.log(res)
    const { code, data, msg } = res.data
    if (code === 0) {
      this.restaurantList = data.shopList
    } else {
      Toast.fail(msg)
    }
  })
}
