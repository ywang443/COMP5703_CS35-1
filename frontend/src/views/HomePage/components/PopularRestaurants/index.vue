<template>
  <div class="popular_restaurants">
    <h2>Popular Restaurants</h2>
    <!-- 热门 -->
    <div class="popular">
      <!-- 小盒子 -->
      <div
        class="popular_box"
        v-for="item in restaurantsData"
        :key="item.id"
        @click="JumpRestaurants(item.id)"
      >
        <!-- 餐厅图片 -->
        <div>
          <van-image width="130" height="100" fit="cover" :src="'http://43.140.198.220:7777/upload/' + item.img" />
        </div>
        <!-- 餐厅名称 -->
        <div class="restaurants_name">
          {{ item.description }}
        </div>
        <!-- 餐厅简介 -->
        <div class="restaurants_info">
          {{ item.description }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'PopularRestaurants',
  props: {
    restaurantsData: {
      type: Array
    }
  },
  methods: {
    // 跳转餐厅
    JumpRestaurants(id) {
      // console.log(id, 123)
      this.$emit('Jump-restaurants', id)
    }
  }
}
</script>

<style lang="scss" scoped>
.popular_restaurants {
  width: 100%;
  .popular {
    display: flex;
    max-width: 100%;
    overflow-x: auto;
    overflow-y: hidden;
    // 小盒子
    .popular_box {
      width: 130px;
      //  border: 1px solid;
      border-radius: 15px;
      box-shadow: 5px 5px 10px #999;
      margin-right: 15px;
      // padding: 5px;
      // 图片
      ::v-deep .van-image__img {
        flex-shrink: 0; // 禁止缩小
        border-radius: 15px;
      }
      // 名称
      .restaurants_name {
        margin: 3px 0;
        font-weight: bold;
        overflow: hidden;
        // 省略号
        text-overflow: ellipsis;
        /*弹性伸缩盒子模型显示*/
        display: -webkit-box;
        /*限制在一个块元素显示文本的行数*/
        -webkit-line-clamp: 1; /*2行*/
        /*设置或检索伸缩盒对象子元素的排列方式*/
        -webkit-box-orient: vertical;
      }
      // 简介
      .restaurants_info {
        padding: 3px;
        font-size: 10px;
        color: rgb(149, 141, 141);
        overflow: hidden;
        // 省略号
        text-overflow: ellipsis;
        /*弹性伸缩盒子模型显示*/
        display: -webkit-box;
        /*限制在一个块元素显示文本的行数*/
        -webkit-line-clamp: 2; /*2行*/
        /*设置或检索伸缩盒对象子元素的排列方式*/
        -webkit-box-orient: vertical;
      }
    }
  }
}
</style>
