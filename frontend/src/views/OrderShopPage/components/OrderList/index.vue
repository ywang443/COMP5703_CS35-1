<template>
  <div class="order_list">
    <div
      class="order_crad"
      :class="item.status ? 'status_color' : ''"
      v-for="item in orderList"
      :key="item.id"
    >
      <div class="left">
        <van-image :src="'http://43.140.198.220:7777/upload/' + item.img" />
      </div>
      <div class="right">
        <div @click="displayUserInfo(item.id)">
          Tables:
          <span>{{ item.tableName }}</span>
          <van-icon name="friends" />
        </div>
        <div class="status">
          Status:
          <span>
            {{ item.status === 1 ? 'Available Now' : 'Not available' }}
          </span>
          <van-switch
            v-model="item.status"
            size="12px"
            @change="switchStatus(item)"
          />
        </div>
        <div>
          Price:
          <span>${{ item.price }}</span>
        </div>
        <div>
          Time:
          <span>{{ item.time }}</span>
        </div>
      </div>
    </div>
    <!-- <van-action-sheet v-model="shop" title="标题">
      <div class="content">内容</div>
    </van-action-sheet> -->
  </div>
</template>

<script>
export default {
  name: 'OrderList',
  data() {
    return {
      status: true
    }
  },
  props: {
    orderList: {
      type: Array
    }
  },
  mounted() {
    this.init()
  },
  methods: {
    initStatus(status) {
      console.log(status)
    },
    // 显示用户信息
    displayUserInfo(id) {
      // console.log(id, 12)
      this.$emit('display-userInfo', id)
    },
    // 切换状态
    switchStatus(id) {
      this.$emit('switch-status', id)
    }
  }
}
</script>

<style lang="scss" scoped>
.order_list {
  .order_crad {
    display: flex;
    align-items: center;
    padding-bottom: 10px;
    margin-top: 15px;
    padding: 5px;
    border-radius: 10px;
    border-bottom: 1px solid rgb(121, 115, 115);
    color: rgb(121, 115, 115);
    .left {
      ::v-deep .van-image__img {
        width: 100px;
        height: 100px;
        border-radius: 10px;
      }
    }
    .right {
      margin-left: 20px;
      .status {
        display: flex;
        align-items: center;
        .van-switch {
          margin-left: 10px;
        }
      }
      div {
        margin: 10px 0;
      }
      span {
        font-weight: bold;
        margin-right: 10px;
      }
    }
  }
}
.status_color {
  color: #000 !important;
  border: 1px solid #3f8bfe;
}
</style>
