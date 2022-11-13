import Vue from 'vue'
import VueRouter from 'vue-router'
import LoginPage from '@/views/LoginPage'

Vue.use(VueRouter)

// 核心：解决代码冗余
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}

const routes = [
  // 登录页
  {
    path: '/',
    name: 'login',
    component: LoginPage
  },
  // **********消费者********
  // 消费者登录之后内容
  {
    path: '/welcome',
    component: () => import('../views/welcome'),
    // 配置一个默认路由地址
    redirect: 'home',
    children: [
      // 定义路由嵌套组件
      {
        path: '/home', // 首页
        component: () => import('../views/HomePage')
      },
      {
        path: '/search', // 搜索页
        component: () => import('../views/SearchPage')
      },
      {
        path: '/like', // 喜欢页
        component: () => import('../views/LikePage')
      },
      {
        path: '/mail', // 邮件页
        component: () => import('../views/MailPage')
      },
      {
        path: '/user', // 用户页
        component: () => import('../views/UserPage')
      }
    ]
  },
  {
    path: '/home/all', // 查看全部热门餐厅
    name: 'seeAll',
    component: () => import('../views/HomePage/page/RecommendedAll')
  },
  {
    path: '/home/SearchProposal', // 搜索建议
    name: 'SearchProposal',
    component: () => import('../views/HomePage/page/SearchProposal')
  },
  {
    path: '/home/mapAssembly', // 地图
    name: 'MapAssembly',
    component: () => import('../views/HomePage/components/MapAssembly')
  },
  {
    path: '/home/filterAssembly', // 过滤
    name: 'FilterAssembly',
    component: () => import('../views/HomePage/page/FilterAssembly')
  },
  {
    path: '/home/popular', // 热门目的
    name: 'PopularDestinations',
    component: () => import('../views/HomePage/page/PopularDestinations')
  },
  {
    path: '/home/details', // 餐厅详情
    name: 'RestaurantDetails',
    component: () => import('../views/HomePage/page/RestaurantDetails')
  },
  {
    path: '/mail/info', // 邮件信息
    name: 'mail',
    component: () => import('../views/MailPage/page/MailInfo')
  },
  {
    path: '/home/order', // 下单
    name: 'LowerOrderPage',
    component: () => import('../views/LowerOrderPage')
  },
  {
    path: '/register', // 注册
    name: 'RegisterPage',
    component: () => import('../views/RegisterPage')
  },
  // *********商家**********
  {
    path: '/welcome/shop',
    component: () => import('../views/WelcomeShop'),
    // 配置一个默认路由地址
    redirect: 'home/shop',
    children: [
      // 定义路由嵌套组件
      {
        path: '/home/shop', // 首页
        component: () => import('../views/HomeShopPage')
      },
      {
        path: '/order/shop', // 搜索页
        component: () => import('../views/OrderShopPage')
      },
      {
        path: '/mail/shop', // 邮件页
        component: () => import('../views/MailShopPage')
      },
      {
        path: '/user/shop', // 用户页
        component: () => import('../views/UserShopPage')
      }
    ]
  }
]

const router = new VueRouter({
  mode: 'hash', // history
  base: process.env.BASE_URL,
  routes
})

// 配置路由守卫 ，第一个参数去往目标路由对象，第二个参数是来源的路由对象，第三个参数是接下来执行的操作
router.beforeEach((to, form, next) => {
  // 如果访问的是登录页，直接放行
  if (to.path === '/' || to.path === '/register') {
    return next()
  }
  // 判断用户是否登录（本地缓存是否有token)  getItem获取
  const userinfo = localStorage.getItem('token')
  if (!userinfo) {
    // 没有token的话就跳转到登录页
    return next('/')
  }
  // 有token就放行
  next()
})

export default router
