<template>
  <div class="recommended">
    <!-- 标题 -->
    <div class="title_name">
      <h2>Recommended</h2>
      <h2 @click="seeWhole">See All</h2>
    </div>
    <!-- 餐厅排行 -->
    <div class="restaurant_ranking">
      <!-- 卡片 -->
      <div
        class="restaurant_card"
        v-for="item in rankingData"
        :key="item.id"
        @click="recommendDetails(item.id)"
      >
        <!-- 左边 -->
        <div class="left">
          <van-image
            width="100"
            height="100"
            :src="'http://43.140.198.220:7777/upload/' + item.img"
          />
        </div>
        <!-- 右边 -->
        <div class="right">
          <!-- 餐厅信息 -->
          <div class="restaurant_info">
            <!-- 名称 -->
            <div class="name">{{ item.name }}</div>
            <!-- 评分 -->
            <div class="score">
              <span><van-icon name="star" /></span>
              {{ item.rate }}
            </div>
          </div>
          <!-- 餐厅简介 -->
          <div class="brief_introduction">
            {{ item.description }}
          </div>
          <!-- 人均消费 -->
          <div class="per_capita">AUD &nbsp; {{ item.average }}/Person</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'RecomMended',
  props: {
    rankingData: {
      type: Array
    }
  },
  methods: {
    // 进入餐厅详情
    recommendDetails(id) {
      this.$emit('recommend-details', id)
    },
    // 查看全部
    seeWhole() {
      console.log(123)
      this.$emit('see-whole')
    }
  }
}
</script>

<style lang="scss" scoped>
.recommended {
  margin-bottom: 50px;
  .title_name {
    display: flex;
    justify-content: space-between;
  }
  // 排行
  .restaurant_ranking {
    // 卡片
    .restaurant_card {
      display: flex;
      // justify-content: space-between;

      background-color: white;
      padding: 10px;
      border-radius: 15px;
      margin-bottom: 10px;
      // 左
      .left {
        margin-right: 10px;
        ::v-deep .van-image__img {
          border-radius: 15px;
        }
      }
      // 右
      .right {
        min-width: 50%;
        margin-left: 20px;
        display: flex;
        justify-content: space-between;
        flex-direction: column;
        // 餐厅信息
        .restaurant_info {
          display: flex;
          justify-content: space-between;
          // 名称
          .name {
            font-weight: bold;
            width: 60%;
            display: -webkit-box; //使用了flex，需要加
            overflow: hidden; //超出隐藏
            word-break: break-all; //纯英文、数字、中文
            text-overflow: ellipsis; //省略号
            -webkit-box-orient: vertical; //垂直
            -webkit-line-clamp: 1; //显示一行
            white-space: pre-line; //合并空白符序列，但是保留换行符。(如果要使用这个垂直和显示几行的代码也要写)
          }
          // 评分
          .score {
            .van-icon {
              color: #fac232;
            }
          }
        }
        // 餐厅简介
        .brief_introduction {
          // width: 40%;
          // overflow: hidden;
          // // 省略号
          // text-overflow: ellipsis;
          // /*弹性伸缩盒子模型显示*/
          // display: -webkit-box;
          // /*限制在一个块元素显示文本的行数*/
          // -webkit-line-clamp: 2; /*2行*/
          // /*设置或检索伸缩盒对象子元素的排列方式*/
          // -webkit-box-orient: vertical;

          display: -webkit-box; //使用了flex，需要加
          overflow: hidden; //超出隐藏
          word-break: break-all; //纯英文、数字、中文
          text-overflow: ellipsis; //省略号
          -webkit-box-orient: vertical; //垂直
          -webkit-line-clamp: 2; //显示一行
          white-space: pre-line; //合并空白符序列，但是保留换行符。(如果要使用这个垂直和显示几行的代码也要写)
        }
        // 人均
        .per_capita {
          color: #585de4;
        }
      }
    }
  }
}
</style>
