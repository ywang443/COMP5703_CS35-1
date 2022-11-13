<template>
  <div class="carousel_chart">
    <van-swipe class="my-swipe" :autoplay="3000" indicator-color="white">
      <van-swipe-item v-for="item in CarouselImg" :key="item.id">
        <!-- <van-image
          fit="fill"
          :src="'http://43.140.198.220:7777/upload/' + item.img"
        /> -->
        <van-image
          width="21rem"
          height="10rem"
          fit="fill"
          :src="'http://43.140.198.220:7777/upload/' + item.img"
        />
      </van-swipe-item>
    </van-swipe>
  </div>
</template>

<script>
export default {
  name: 'CarouselChart',
  props: {
    CarouselImg: {
      type: Array
    }
  },
  created() {
    // console.log(this.CarouselImg)
  }
}
</script>

<style lang="scss" scoped>
.carousel_chart {
  width: 100%;
  height: 150px;
  margin: 0 auto;
  .van-swipe {
    width: 100%;
    .van-swipe-item {
      width: 100%;
      ::v-deep .van-image__img {
        width: 100%;
        height: 200px;
        border-radius: 15px;
      }
    }
  }
}
</style>
