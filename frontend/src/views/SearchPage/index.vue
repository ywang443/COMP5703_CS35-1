<template>
  <div class="search_page">
    <!-- 页头 -->
    <div class="header">
      <h2>My Reservations</h2>
      <van-icon name="clock-o" />
    </div>
    <!-- <h1>这是没有订单数据的时候显示的</h1> -->
    <!-- 内容 -->
    <div class="content" v-if="orderList.length === 0">
      <!-- 海报 -->
      <div class="picture">
        <van-image width="10rem" height="10rem" fit="fill" :src="img" />
      </div>
      <!-- 标题 -->
      <div class="text_center"><h3>Reservations Empty</h3></div>
      <div class="text_center">
        Your order is empty. Search restaurants and start reservations.
      </div>
      <div class="text_center">
        <van-button type="info" @click="jumpSearch">
          Reserve an reservation
        </van-button>
      </div>
      <div class="text_center">Can't find your reservations?</div>
      <div class="text_center" @click="seekHelp">Get Help</div>
    </div>
    <!-- ***** -->
    <!-- <h1>这是有订单数据的时候显示的</h1> -->
    <!-- 预定列表 -->
    <div class="order_list" v-else>
      <!-- 页头 -->
      <div><h3>Active Booking</h3></div>
      <!-- 列表 -->
      <div><OrderList :orderList="orderList"></OrderList></div>
    </div>
    <van-divider
      :style="{ color: '#1989fa', borderColor: '#1989fa', padding: '0 16px' }"
    >
      到底了哦~
    </van-divider>
  </div>
</template>

<script>
import OrderList from './components/OrderList'
import { Toast } from 'vant'
import { postUserOrder } from '@/utils/user'
export default {
  name: 'SearchPage',
  components: {
    OrderList
  },
  data() {
    return {
      img: require('@/assets/image/yuding2.png'),
      // 订单列表
      orderList: [
        // {
        //   id: '9281025', // 订单id
        //   imgSrc: require('@/assets/image/canting1.jpg'), // 餐厅图片
        //   name: 'Home Thai Restaurant', // 餐厅名称
        //   location: 'Sembalun L ombok', // 位置
        //   quantity: '2', // 人数
        //   date: ' Fri, 23 Sep at 17:30' // 日期
        // },
        // {
        //   id: '9281025', // 订单id
        //   imgSrc: require('@/assets/image/canting2.jpg'), // 餐厅图片
        //   name: 'Home Thai Restaurant', // 餐厅名称
        //   location: 'Sembalun L ombok', // 位置
        //   quantity: '2', // 人数
        //   date: ' Fri, 23 Sep at 17:30' // 日期
        // },
        // {
        //   id: '9281025', // 订单id
        //   imgSrc: require('@/assets/image/canting3.jpg'), // 餐厅图片
        //   name: 'Home Thai Restaurant', // 餐厅名称
        //   location: 'Sembalun L ombok', // 位置
        //   quantity: '2', // 人数
        //   date: ' Fri, 23 Sep at 17:30' // 日期
        // },
        // {
        //   id: '9281025', // 订单id
        //   imgSrc: require('@/assets/image/canting4.jpg'), // 餐厅图片
        //   name: 'Home Thai Restaurant', // 餐厅名称
        //   location: 'Sembalun L ombok', // 位置
        //   quantity: '2', // 人数
        //   date: ' Fri, 23 Sep at 17:30' // 日期
        // },
        // {
        //   id: '9281025', // 订单id
        //   imgSrc: require('@/assets/image/canting1.jpg'), // 餐厅图片
        //   name: 'Home Thai Restaurant', // 餐厅名称
        //   location: 'Sembalun L ombok', // 位置
        //   quantity: '2', // 人数
        //   date: ' Fri, 23 Sep at 17:30' // 日期
        // },
        // {
        //   id: '9281025', // 订单id
        //   imgSrc: require('@/assets/image/canting2.jpg'), // 餐厅图片
        //   name: 'Home Thai Restaurant', // 餐厅名称
        //   location: 'Sembalun L ombok', // 位置
        //   quantity: '2', // 人数
        //   date: ' Fri, 23 Sep at 17:30' // 日期
        // },
        // {
        //   id: '9281025', // 订单id
        //   imgSrc: require('@/assets/image/canting3.jpg'), // 餐厅图片
        //   name: 'Home Thai Restaurant', // 餐厅名称
        //   location: 'Sembalun L ombok', // 位置
        //   quantity: '2', // 人数
        //   date: ' Fri, 23 Sep at 17:30' // 日期
        // },
        // {
        //   id: '9281025', // 订单id
        //   imgSrc: require('@/assets/image/canting4.jpg'), // 餐厅图片
        //   name: 'Home Thai Restaurant', // 餐厅名称
        //   location: 'Sembalun L ombok', // 位置
        //   quantity: '2', // 人数
        //   date: ' Fri, 23 Sep at 17:30' // 日期
        // }
      ]
    }
  },
  created() {
    this.obtainUserOrderList()
  },
  methods: {
    // 获取用户订单列表
    obtainUserOrderList() {
      postUserOrder().then(res => {
        console.log(res)
        const { code, data, msg } = res.data
        if (code === 0) {
          this.orderList = data.orders
        } else {
          Toast.fail(msg)
        }
      })
    },
    // 跳转到搜索页
    jumpSearch() {
      this.$router.push({ path: '/home/SearchProposal' })
    },
    // 寻找帮助
    seekHelp() {
      Toast.fail('我也帮不了你哦~')
    }
  }
}
</script>

<style lang="scss" scoped>
.search_page {
  // width: 100%;
  height: 100%;
  padding: 10px;
  overflow-x: hidden;
  // 内容
  .text_center:nth-child(3) {
    margin: 20px 0;
  }
  .text_center:nth-child(4) {
    margin: 10px 0;
    .van-button--normal {
      border-radius: 10px;
    }
  }
  .text_center:nth-child(5) {
    margin-top: 70px;
  }
  .text_center:nth-child(6) {
    margin-top: 30px;
    color: #3337ab;
    font-weight: bold;
  }
  .content {
    // 图片
    .picture {
      margin-top: 30px;
      text-align: center;
    }
  }
  // 页头
  .header {
    display: flex;
    justify-content: space-between;
    flex-direction: row;
    align-items: center;
  }
}
.text_center {
  text-align: center;
}
</style>
