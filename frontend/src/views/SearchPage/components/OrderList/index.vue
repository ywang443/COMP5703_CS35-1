<template>
  <div class="orderlist">
    <div class="orderlist_card" v-for="item in orderList" :key="item.id">
      <!-- 上 -->
      <div class="upper">
        <div class="left">
          <div class="left_id">R ESTAURANT</div>
          <div>ID {{ item.appointmentId }}</div>
        </div>
        <div class="rigtn">
          <div><span>Checkin</span></div>
          <div><van-icon name="wap-nav" /></div>
        </div>
      </div>
      <!-- 中 -->
      <div class="middle">
        <div class="left">
          <van-image :src="'http://43.140.198.220:7777/upload/' + item.img" />
        </div>
        <div class="right">
          <!-- 标题 -->
          <div class="title">
            <van-icon name="shop-collect" />
            {{ item.shopName }}
          </div>
          <!-- 电话 -->
          <div class="phone" @click="callUser(item.phone)">
            <van-icon name="phone" />
            {{ item.phone }}
          </div>
          <!-- 定位 -->
          <div class="location">
            <van-icon name="map-marked" />
            {{ item.address }}
          </div>
          <!-- 信息 -->
          <div class="order_info">
            <!-- 人数 -->
            <div class="quantity">
              <van-icon name="manager" />
              {{ item.quantity }}
            </div>
            <!-- 日期 -->
            <div>
              <van-icon name="todo-list" />
              {{ item.date }}
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'OrderList',
  props: {
    orderList: {
      type: Array
    }
  },
  methods: {
    // 拨打电话
    callUser(tel) {
      window.location.href = 'tel://' + tel
    },
    // 格式时间
    formatTime(time) {
      // console.log(time)
      const a = time.substring(0, 13)
      // console.log(a)
      return a
    }
  }
}
</script>

<style lang="scss" scoped>
.orderlist {
  padding-bottom: 50px;
  .orderlist_card {
    margin-top: 40px;
    // 中
    .middle {
      display: flex;
      align-items: center;
      padding: 20px 0;
      border-bottom: 1px solid #4f4f65;
      .left {
        ::v-deep .van-image__img {
          width: 130px;
          height: 130px;
          border-radius: 10px;
        }
      }
      .right {
        margin-left: 20px;
        .title {
          h4 {
            line-height: 0px;
          }
        }
        .phone {
          margin: 3px 0;
        }
        // 信息
        .order_info {
          width: 100%;
          margin-top: 10px;
          display: flex;
          color: #5a5a5d;
          // 数量
          .quantity {
            min-width: 20%;
            margin-right: 10px;
          }
        }
        // 定位
        .location {
          color: #5a5a5d;
          .van-icon {
            color: #fc5f5f;
          }
        }
      }
    }
    // 上
    .upper {
      display: flex;
      justify-content: space-between;
      align-items: center;
      border-bottom: 1px solid #4f4f65;

      padding: 20px 0;
      .left {
        color: #4f4f65;
        .left_id {
          font-size: 18px;
          font-weight: bold;
          color: #2c30b9;
          padding-bottom: 15px;
        }
      }
      .rigtn {
        display: flex;
        span {
          padding: 7px;
          border-radius: 30px;
          background-color: rgba(147, 193, 222, 0.2);
          color: #2c30b9;
          margin-right: 20px;
        }
      }
    }
  }
}
</style>
