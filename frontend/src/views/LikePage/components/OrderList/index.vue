<template>
  <div class="orderlist">
    <div class="orderlist_card" v-for="item in orderList" :key="item.id">
      <!-- 上 -->
      <!-- <div class="upper">
        <div class="left">
          <div class="left_id">R ESTAURANT</div>
          <div>ID {{ item.id }}</div>
        </div>
        <div class="rigtn">
          <div><span>Checkin</span></div>
          <div><van-icon name="wap-nav" /></div>
        </div>
      </div> -->
      <!-- 中 -->
      <div class="middle">
        <div class="left">
          <van-image :src="'http://43.140.198.220:7777/upload/' + item.img" />
        </div>
        <div class="right">
          <!-- 标题 -->
          <div class="titel">
            <h4>{{ item.name }}</h4>
            <van-icon name="clear" @click="deleteOrder(item.id)" />
          </div>
          <!-- 定位 -->
          <div class="location">
            <van-icon name="map-marked" />
            {{ item.address }}
          </div>
          <!-- 信息 -->
          <div class="order_info">
            <!-- 人数 -->
            <div class="quantity">AUD {{ item.average }}</div>
            <!-- 日期 -->
            <div>
              <van-button type="info" @click="placeAnOrder(item.id)">
                Book
              </van-button>
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
    // 下单
    placeAnOrder(id) {
      // Toast('跳转下单页...')
      this.$emit('placeAn-order', id)
    },
    // 删除订单
    deleteOrder(id) {
      this.$emit('delete-order', id)

      // Toast.fail('不可以删除哦~')
    }
  }
}
</script>

<style lang="scss" scoped>
.orderlist {
  // width: 100%;
  // padding-bottom: 50px;
  .orderlist_card {
    // width: 100%;
    margin-bottom: 40px;
    // 中
    .middle {
      // width: 100%;
      display: flex;
      padding: 20px 0;
      border-bottom: 1px solid #4f4f65;
      .left {
        // width: 30%;
        ::v-deep .van-image__img {
          width: 130px;
          height: 130px;
          border-radius: 10px;
        }
      }
      .right {
        // width: 60%;
        // margin-left: 20px;
        margin-left: 5%;
        h4 {
          line-height: 5px;
        }
        // 标题
        .titel {
          display: flex;
          justify-content: space-between;
          align-items: center;
        }
        // 信息
        .order_info {
          margin-top: 10px;
          display: flex;
          justify-content: space-between;
          align-items: center;
          color: #2b2fb5;
          font-weight: bold;
          .van-button--normal {
            border-radius: 10px;
            padding: 15px 25px;
          }
          // 数量
          .quantity {
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
