<template>
  <div class="map_filter">
    <!-- 地图过滤 -->
    <div class="mapFilter">
      <div class="left" @click="jumpMap">
        <van-icon name="edit" />
        Map
      </div>
      <div class="right" @click="jumpFilter">
        <van-icon name="apps-o" />
        Filter
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MapFilter',
  methods: {
    // 点击过滤
    jumpFilter() {
      this.$router.push({
        path: '/home/filterAssembly'
      })
    },
    // 点击地图
    jumpMap() {
      this.$router.push({
        path: '/home/mapAssembly'
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.map_filter {
  // 地图过滤
  .mapFilter {
    width: 40%;
    display: flex;
    justify-content: space-around;
    background-color: #6569d8;
    padding: 15px;
    border-radius: 30px;
    // 固定
    // width: 100%;
    position: fixed;
    bottom: 7%;
    right: 25%;

    // 左
    .left {
      padding-right: 12px;
      border-right: 1px solid;
    }
  }
}
</style>
