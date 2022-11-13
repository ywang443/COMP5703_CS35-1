import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Vant from 'vant'
import 'vant/lib/index.css'
import './rem/rem' // 转rem
// import Base64 from 'js-base64' // 引入密码加密
import md5 from 'js-md5' // 引入加密
import * as echarts from 'echarts' // 引入数据可视化
import VConsole from 'vconsole' // 引入手机调试工具
// ！！*仅用于开发模式，上线时注释掉*！！
const vConsole = new VConsole()
Vue.use(vConsole)
Vue.config.productionTip = false

Vue.prototype.$echarts = echarts // 挂载
// Vue.use(Base64)
const Base64 = require('js-base64').Base64

Vue.prototype.$md5 = md5 // md5加密

Vue.use(Vant)

new Vue({
  router,
  store,
  Base64,
  render: h => h(App)
}).$mount('#app')
