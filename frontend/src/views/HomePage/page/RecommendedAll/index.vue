<template>
  <div class="recommended_all">
    <!-- 导航栏 -->
    <div class="nav_bar">
      <van-nav-bar
        left-text="Recommended"
        left-arrow
        @click-left="onClickLeft"
      />
    </div>
    <!-- 分类 -->
    <div class="classification">
      <span
        :class="[activeIndex === index ? 'span_color' : '']"
        v-for="(item, index) in classificationData"
        :key="index"
        @click="choiceClassification(index)"
      >
        {{ item }}
      </span>
    </div>
    <!-- 餐厅排行 -->
    <!-- 餐厅排行 -->
    <div class="restaurant_ranking">
      <!-- 卡片 -->
      <div
        class="restaurant_card"
        v-for="item in rankingAllData"
        :key="item.id"
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
              {{ item.average }}
            </div>
          </div>
          <!-- 餐厅简介 -->
          <div class="brief_introduction">
            {{ item.description }}
          </div>
          <!-- 人均消费 -->
          <div class="per_capita">AUD &nbsp; {{ item.rate }}/Person</div>
        </div>
      </div>
    </div>
    <!-- 地图过滤 -->
    <MapFilter></MapFilter>
    <!-- 分割 -->
    <div>
      <van-divider
        :style="{ color: '#1989fa', borderColor: '#1989fa', padding: '0 16px' }"
      >
        已经到底了哦~
      </van-divider>
    </div>
  </div>
</template>

<script>
import MapFilter from '../../components/MapFilter'
import { Toast } from 'vant'
import { getAllRestaurantList } from '@/utils/user'

export default {
  name: 'RecommendedAll',
  components: {
    MapFilter
  },
  data() {
    return {
      // 定义分类标签数据
      classificationData: ['All', 'Chinese food', 'Western-style food'],
      // 定义排行榜数据
      rankingAllData: [],
      // activeIndex
      activeIndex: 0
    }
  },
  created() {
    this.obtainAllRanking()
  },
  methods: {
    // 获取排行榜全部数据
    obtainAllRanking() {
      getAllRestaurantList().then(res => {
        console.log(res)
        const { code, data, msg } = res.data
        if (code === 0) {
          this.rankingAllData = data.shopRankingAll
        } else {
          Toast.fail(msg)
        }
      })
    },
    // // 点击过滤
    // jumpFilter() {
    //   this.$router.push({
    //     path: '/home/filterAssembly'
    //   })
    // },
    // // 点击地图
    // jumpMap() {
    //   this.$router.push({
    //     path: '/home/mapAssembly'
    //   })
    // },
    // 点击选择分类
    choiceClassification(index) {
      //   console.log(index)
      this.activeIndex = index
      Toast.fail('暂无数据！')
    },
    // 点击返回
    onClickLeft() {
      //   console.log(123)
      //   this.$router.(-1)
      this.$router.back()
    }
  }
}
</script>

<style lang="scss" scoped>
.recommended_all {
  height: 100%;
  overflow-x: auto;
  // 地图过滤
  // .mapFilter {
  //   width: 40%;
  //   display: flex;
  //   justify-content: space-around;
  //   background-color: #585de4;
  //   padding: 15px;
  //   border-radius: 30px;
  //   // 固定
  //   // width: 100%;
  //   position: fixed;
  //   bottom: 7%;
  //   right: 25%;

  //   // 左
  //   .left {
  //     padding-right: 12px;
  //     border-right: 1px solid;
  //   }
  // }
  // 分类
  .classification {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    justify-content: space-around;
    margin: 10px 0;

    span {
      //   margin-top: 10px;
      border-radius: 15px;
      padding: 7px;
      background-color: #f4f4f6;
      margin: 5px 0;
    }
    .span_color {
      //  background-color: #d3ceef;
      border: 1px solid #5140c1;
      box-shadow: 3px 3px 10px #d3ceef;
    }
  }
  // 餐厅排行
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
        margin-left: 20px;
        min-width: 50%;
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
  .nav_bar {
    .van-nav-bar {
      ::v-deep .van-nav-bar__text,
      van-nav-bar__arrow {
        color: #000 !important;
      }
    }
  }
}
</style>
