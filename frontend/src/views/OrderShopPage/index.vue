<template>
  <div class="order_shop_page">
    <OrderList
      :orderList="orderList"
      :isShowInfo="isShowInfo"
      @switch-status="changeTableState"
      @display-userInfo="obtainUserReserveInfo"
      v-if="orderList.length !== 0"
    ></OrderList>
    <van-empty description="暂无此类数据哦~" v-else />
    <van-action-sheet
      v-model="isShowInfo"
      title="User subscription information"
    >
      <div class="content">
        <div class="info">
          <div>
            <van-icon name="user-o" />
            UserName：{{ user_info.name }}
          </div>
        </div>
        <div class="info">
          <div>
            <van-icon name="guide-o" />
            UserMail：{{ user_info.mail }}
          </div>
        </div>
        <div class="info" @click="callUser(user_info.phone)">
          <div>
            <van-icon name="phone-circle-o" />
            UserPhone：{{ user_info.phone }}
          </div>
        </div>
        <div class="info">
          <div>
            <van-icon name="comment-circle-o" />
            UsermMessage：{{ user_info.memo }}
          </div>
        </div>
        <div class="info">
          <div>
            <van-icon name="underway-o" />
            ReserveTime：{{ user_info.createdAt }}
          </div>
        </div>
        <!-- <div class="info"> -->
        <div class="info">
          <van-icon name="underway-o" />
          Have meals Time：{{ user_info.date }}
        </div>

        <div class="info">
          <div>
            <van-icon name="friends-o" />
            Number of people：{{ user_info.quantity }}
          </div>
        </div>
        <div class="info">
          <div>
            <van-icon name="apps-o" />
            Number of tables：{{ user_info.tables }}
          </div>
        </div>
        <!-- </div> -->
      </div>
    </van-action-sheet>
  </div>
</template>

<script>
import { postShopOrder, postShopOrderInfo, postTableStatus } from '@/utils/shop'
import OrderList from './components/OrderList'
import { Toast } from 'vant'

export default {
  name: 'OrderShopPage',
  components: { OrderList },
  data() {
    return {
      isShowInfo: false, // 是否显示预定详细信息
      user_info: {}, // 用户预定详细信息
      // 订单数据
      orderList: [
        // {
        //   img: require('@/assets/image/canting1.jpg'), // 桌子图片
        //   tables: 'Tables 01', // 桌号
        //   status: 'Available Now', // 状态
        //   isShowStatus: false, // 状态
        //   price: '50.00', // 消费
        //   time: '17:00-19:00',
        //   order_id: 123, // 订单id,
        //   table_id: 341 // 桌子id
        // },
        // {
        //   img: require('@/assets/image/canting2.jpg'), // 桌子图片
        //   tables: 'Tables 02', // 桌号
        //   status: 'Available Now', // 状态
        //   isShowStatus: true, // 状态
        //   price: '50.00', // 消费
        //   time: '17:00-19:00'
        // },
        // {
        //   img: require('@/assets/image/canting3.jpg'), // 桌子图片
        //   tables: 'Tables 03', // 桌号
        //   status: 'Available Now', // 状态
        //   isShowStatus: false, // 状态
        //   price: '50.00', // 消费
        //   time: '17:00-19:00'
        // },
        // {
        //   img: require('@/assets/image/canting4.jpg'), // 桌子图片
        //   tables: 'Tables 04', // 桌号
        //   status: 'Available Now', // 状态
        //   isShowStatus: true, // 状态
        //   price: '50.00', // 消费
        //   time: '17:00-19:00'
        // },
        // {
        //   img: require('@/assets/image/canting1.jpg'), // 桌子图片
        //   tables: 'Tables 05', // 桌号
        //   status: 'Available Now', // 状态
        //   isShowStatus: false, // 状态
        //   price: '50.00', // 消费
        //   time: '17:00-19:00'
        // },
        // {
        //   img: require('@/assets/image/canting2.jpg'), // 桌子图片
        //   tables: 'Tables 06', // 桌号
        //   status: 'Available Now', // 状态
        //   isShowStatus: true, // 状态
        //   price: '50.00', // 消费
        //   time: '17:00-19:00'
        // },
        // {
        //   img: require('@/assets/image/canting3.jpg'), // 桌子图片
        //   tables: 'Tables 07', // 桌号
        //   status: 'Available Now', // 状态
        //   isShowStatus: true, // 状态
        //   price: '50.00', // 消费
        //   time: '17:00-19:00'
        // },
        // {
        //   img: require('@/assets/image/canting4.jpg'), // 桌子图片
        //   tables: 'Tables 08', // 桌号
        //   status: 'Available Now', // 状态
        //   isShowStatus: false, // 状态
        //   price: '50.00', // 消费
        //   time: '17:00-19:00'
        // },
        // {
        //   img: require('@/assets/image/canting1.jpg'), // 桌子图片
        //   tables: 'Tables 09', // 桌号
        //   status: 'Available Now', // 状态
        //   isShowStatus: false, // 状态
        //   price: '50.00', // 消费
        //   time: '17:00-19:00'
        // },
        // {
        //   img: require('@/assets/image/canting2.jpg'), // 桌子图片
        //   tables: 'Tables 10', // 桌号
        //   status: 'Available Now', // 状态
        //   isShowStatus: true, // 状态
        //   price: '50.00', // 消费
        //   time: '17:00-19:00'
        // },
        // {
        //   img: require('@/assets/image/canting3.jpg'), // 桌子图片
        //   tables: 'Tables 11', // 桌号
        //   status: 'Available Now', // 状态
        //   isShowStatus: false, // 状态
        //   price: '50.00', // 消费
        //   time: '17:00-19:00'
        // },
        // {
        //   img: require('@/assets/image/canting4.jpg'), // 桌子图片
        //   tables: 'Tables 12', // 桌号
        //   status: 'Available Now', // 状态
        //   isShowStatus: false, // 状态
        //   price: '50.00', // 消费
        //   time: '17:00-19:00'
        // }
      ]
    }
  },
  created() {
    this.obtainbShopOrderList()
  },
  methods: {
    // 拨打电话
    callUser(tel) {
      window.location.href = 'tel://' + tel
    },
    // 获取用户预定信息
    obtainUserReserveInfo(id) {
      const data = {
        id: id
      }
      postShopOrderInfo(data).then(res => {
        console.log(res)
        const { code, data, msg } = res.data
        if (code === 0) {
          this.user_info = data.orderDetail
          this.isShowInfo = true
        } else {
          Toast.fail(msg)
        }
      })
    },
    // 更改桌子状态
    changeTableState(item) {
      console.log(item)
      const status = {
        true: 1,
        false: 0
      }
      const data = {
        id: item.id,
        status: status[item.status]
      }
      postTableStatus(data).then(res => {
        console.log(res)
        const { code, msg } = res.data
        if (code === 0) {
          Toast.success(msg)
        } else {
          Toast.fail(msg)
        }
      })
    },
    // 获取商家订单列表
    obtainbShopOrderList() {
      postShopOrder().then(res => {
        console.log(res)
        const { code, data, msg } = res.data
        if (code === 0) {
          this.orderList = data.orders
        } else {
          Toast.fail(msg)
        }
      })
    }
    // 显示用户信息
    // displayUserInfo(id) {
    //   this.isShowInfo = true
    //   console.log(id)
    // }
    // 切换状态
    // switchStatus(id) {
    //   if (this.orderList[id].isShowStatus) {
    //     this.orderList[id].status = 'Available Now'
    //   } else {
    //     this.orderList[id].status = 'Not available'
    //   }
    // }
  }
}
</script>

<style lang="scss" scoped>
.order_shop_page {
  // height: 100%;
  overflow-x: auto;
  padding: 10px;
  margin-bottom: 100px;
  // 预定信息
  .van-action-sheet {
    .content {
      padding: 20px 0px;
      margin-left: 20px;
      text-align: left;
      .info {
        margin: 10px 0;
        display: flex;
        padding-bottom: 10px;
        border-bottom: 1px dashed rgb(186, 178, 178);
      }
      .text {
        width: 50%;
        display: -webkit-box; //使用了flex，需要加
        overflow: hidden; //超出隐藏
        word-break: break-all; //纯英文、数字、中文
        text-overflow: ellipsis; //省略号
        -webkit-box-orient: vertical; //垂直
        -webkit-line-clamp: 2; //显示一行
        white-space: pre-line; //合并空白符序列，但是保留换行符。(如果要使用这个垂直和显示几行的代码也要写)
      }
    }
  }
}
</style>
