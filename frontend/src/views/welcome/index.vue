<template>
  <div class="welcome">
    <router-view />

    <van-tabbar route>
      <van-tabbar-item replace to="/home" icon="home-o"></van-tabbar-item>
      <van-tabbar-item
        replace
        to="/search"
        icon="newspaper-o"
      ></van-tabbar-item>
      <van-tabbar-item replace to="/like" icon="like-o"></van-tabbar-item>
      <van-tabbar-item replace to="/mail" icon="envelop-o"></van-tabbar-item>
      <van-tabbar-item
        replace
        to="/user"
        icon="user-circle-o"
      ></van-tabbar-item>
    </van-tabbar>
  </div>
</template>

<script>
export default {
  name: 'WelcomePpage',
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
.welcome {
  width: 100%;
  height: 100%;
  overflow: auto;
  background-color: rgb(242, 244, 246); // 定义app背景颜色
  .van-tabbar {
    //  margin-top: 100px;
  }
}
</style>
