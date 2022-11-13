<template>
  <div class="restaurant_cred">
    <div
      class="restaurant_box"
      v-for="item in restaurantList"
      :key="item.id"
      @click="jumpDetails(item, item.id)"
    >
      <!-- 图片 -->
      <div class="picture">
        <div>
          <van-image
            :src="'http://43.140.198.220:7777/upload/' + item.img"
            width="23rem"
            height="15rem"
            fit="fill"
          />
        </div>
        <div class="like" @click.stop="switchLike(item.id)">
          <van-icon name="like-o" v-show="!item.isShowLike" />
          <van-icon name="like" v-show="item.isShowLike" />
        </div>
      </div>
      <!-- 标题 -->
      <div class="titel">{{ item.name }}</div>
      <!-- 简介 -->
      <div class="restaurant_info">
        {{ item.description }}
        <span>
          <van-icon name="star" />
          {{ item.rate }}
        </span>
      </div>
      <!-- 人均 -->
      <div class="average">
        <span>IDR {{ item.average }}</span>
        /night
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'RestaurantCred',
  props: {
    restaurantList: { type: Array }
  },
  data() {
    return {
      //   isShowLike: false
    }
  },
  methods: {
    // 跳转餐厅详情
    jumpDetails(item, index) {
      // console.log(item, index)
      // 携带数据
      this.$emit('jump-details', item, index)
    },
    // 点击切换喜欢
    switchLike(id) {
      // console.log(id)
      this.$emit('switch-like', id)
    }
  }
}
</script>

<style lang="scss" scoped>
.restaurant_cred {
  width: 100%;
  margin-top: 10px;
  .restaurant_box {
    margin-bottom: 20px;
    // 标题
    .titel {
      margin-top: 10px;
    }
    // 人均
    .average {
      color: #393b42;
      font-size: 13px;
      span {
        font-size: 15px;
        font-weight: bold;
        color: #3136bc;
      }
    }
    // 简介
    .restaurant_info {
      margin: 5px 0;
      font-size: 13px;
      color: #595c68;
      span {
        margin-left: 15px;
        color: #393b42;
        .van-icon {
          color: #fac232;
        }
      }
    }
    // 图片
    .picture {
      //   width: 90%;
      position: relative;
      ::v-deep .van-image__img {
        width: 97%;
        // height: 220px;
        border-radius: 15px;
      }
      // 喜欢
      .like {
        // z-index: 9999;
        font-size: 25px;
        border-radius: 100%;
        padding: 10px;
        background-color: #ffffff;
        color: #fc5f5f;
        top: 30px;
        right: 30px;
        position: absolute;
      }
    }
  }
}
</style>
