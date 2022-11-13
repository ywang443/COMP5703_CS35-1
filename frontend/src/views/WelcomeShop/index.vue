<template>
  <div class="welcome_shop">
    <router-view />

    <van-tabbar route>
      <van-tabbar-item replace to="/home/shop" icon="home-o"></van-tabbar-item>
      <van-tabbar-item
        replace
        to="/order/shop"
        icon="newspaper-o"
      ></van-tabbar-item>
      <van-tabbar-item
        replace
        to="/mail/shop"
        icon="envelop-o"
      ></van-tabbar-item>
      <van-tabbar-item
        replace
        to="/user/shop"
        icon="user-circle-o"
      ></van-tabbar-item>
    </van-tabbar>
  </div>
</template>

<script>
export default {
  name: 'WelcomeShop',
  data() {
    return {
      active: 0
    }
  },
  methods: {
    // 切换标签
  }
}
</script>

<style lang="scss" scoped>
.welcome_shop {
  width: 100%;
  height: 100%;
  overflow: auto;
  background-color: rgb(242, 244, 246); // 定义app背景颜色
  .van-tabbar {
    //  margin-top: 100px;
  }
}
</style>
