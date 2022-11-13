<template>
  <div class="popular_destinations">
    <!-- 导航栏 -->
    <div>
      <van-nav-bar
        :left-text="$route.query.value"
        left-arrow
        @click-left="onClickLeft"
      />
    </div>
    <!-- 选择 -->
    <div class="choice">
      <!-- 日期 -->
      <div class="choice_date">
        <!-- 图标 -->
        <div class="choice_Icon" @click="choiceDate">
          <van-icon name="notes-o" />
        </div>
        <!-- 内容 -->
        <div class="choice_content" @click="choiceDate">
          {{ startDate }} Jul - {{ endtDate }} Jul
        </div>
        <van-calendar v-model="isShowDate" type="range" @confirm="onConfirm" />
      </div>
      <!--  客人-->
      <div class="choice_date" @click="addQuantity">
        <!-- 图标 -->
        <div class="choice_Icon">
          <van-icon name="contact" />
        </div>
        <!-- 内容 -->
        <div class="choice_content">{{ quantity }} Guest</div>
      </div>
      <!--  房间-->
      <div class="choice_date" @click="addRoom">
        <!-- 图标 -->
        <div class="choice_Icon">
          <van-icon name="shop-o" />
        </div>
        <!-- 内容 -->
        <div class="choice_content">{{ room }} Room</div>
      </div>
    </div>
    <!-- 餐厅卡片 -->
    <div>
      <RestaurantCred
        :restaurantList="restaurantList"
        @switch-like="switchLike"
        @jump-details="jumpDetails"
        v-if="restaurantList.length !== 0"
      ></RestaurantCred>
      <van-empty description="暂无此类内容哦~" v-else />
    </div>
    <!-- 地图 过滤 -->
    <div><MapFilter></MapFilter></div>
  </div>
</template>

<script>
import RestaurantCred from '../../components/RestaurantCred'
import MapFilter from '../../components/MapFilter'
import { Toast } from 'vant'
import {
  getSearch,
  getUserShopCategory,
  postRestaurantStatus
} from '@/utils/user'
import { searchInfo } from '../../utils/search'
export default {
  name: 'PopularDestinations',
  components: { RestaurantCred, MapFilter },
  data() {
    return {
      isShowDate: false, // 选择日期显示隐藏
      startDate: '23', // 开始时间
      endtDate: '27', // 结束时间
      quantity: 1, // 人数
      room: 1, // 人数
      // 定义餐厅数据
      restaurantList: [
        // {
        //   imgSrc: require('@/assets/image/canting1.jpg'), // 图片地址
        //   titel: 'L ound Villa', // 标题
        //   info: 'Batu, Malang', // 简介
        //   score: '4.8', // 评分
        //   average: '541.000', // 人均
        //   isShowLike: false // 喜欢
        // },
        // {
        //   imgSrc: require('@/assets/image/canting2.jpg'), // 图片地址
        //   titel: 'L ound Villa', // 标题
        //   info: 'Batu, Malang', // 简介
        //   score: '4.8', // 评分
        //   average: '541.000', // 人均
        //   isShowLike: false // 喜欢
        // },
        // {
        //   imgSrc: require('@/assets/image/canting3.jpg'), // 图片地址
        //   titel: 'L ound Villa', // 标题
        //   info: 'Batu, Malang', // 简介
        //   score: '4.8', // 评分
        //   average: '541.000', // 人均
        //   isShowLike: false // 喜欢
        // },
        // {
        //   imgSrc: require('@/assets/image/canting4.jpg'), // 图片地址
        //   titel: 'L ound Villa', // 标题
        //   info: 'Batu, Malang', // 简介
        //   score: '4.8', // 评分
        //   average: '541.000', // 人均
        //   isShowLike: false // 喜欢
        // },
        // {
        //   imgSrc: require('@/assets/image/canting1.jpg'), // 图片地址
        //   titel: 'L ound Villa', // 标题
        //   info: 'Batu, Malang', // 简介
        //   score: '4.8', // 评分
        //   average: '541.000', // 人均
        //   isShowLike: false // 喜欢
        // },
        // {
        //   imgSrc: require('@/assets/image/canting2.jpg'), // 图片地址
        //   titel: 'L ound Villa', // 标题
        //   info: 'Batu, Malang', // 简介
        //   score: '4.8', // 评分
        //   average: '541.000', // 人均
        //   isShowLike: false // 喜欢
        // },
        // {
        //   imgSrc: require('@/assets/image/canting3.jpg'), // 图片地址
        //   titel: 'L ound Villa', // 标题
        //   info: 'Batu, Malang', // 简介
        //   score: '4.8', // 评分
        //   average: '541.000', // 人均
        //   isShowLike: false // 喜欢
        // },
        // {
        //   imgSrc: require('@/assets/image/canting4.jpg'), // 图片地址
        //   titel: 'L ound Villa', // 标题
        //   info: 'Batu, Malang', // 简介
        //   score: '4.8', // 评分
        //   average: '541.000', // 人均
        //   isShowLike: false // 喜欢
        // },
        // {
        //   imgSrc: require('@/assets/image/canting1.jpg'), // 图片地址
        //   titel: 'L ound Villa', // 标题
        //   info: 'Batu, Malang', // 简介
        //   score: '4.8', // 评分
        //   average: '541.000', // 人均
        //   isShowLike: false // 喜欢
        // }
      ]
    }
  },
  created() {
    // console.log(this.$route.query)
    this.obtainSearchInfo()
  },
  mounted() {
    // console.log(this.$route.query)
  },
  methods: {
    // 获取搜索内容
    obtainSearchInfo() {
      if (this.$route.query.type) {
        console.log('类别')
        const data = `type=${this.$route.query.type}`
        getUserShopCategory(data).then(res => {
          console.log(res)
          const { code, data, msg } = res.data
          if (code === 0) {
            this.restaurantList = data.shopList
          } else {
            Toast.fail(msg)
          }
        })
      } else {
        console.log('关键词')
        searchInfo.call(this, this.$route.query.value)
      }
    },
    // 更改餐厅收藏状态
    changeRestaurantCollectionState(id) {},
    // 获取搜索内容
    obtainSearchContent() {
      getSearch().then(res => {
        console.log(res)
      })
    },
    // 跳转餐厅详情
    jumpDetails(item, id) {
      // console.log(item, index)
      // 携带数据跳转
      this.$router.push({
        path: '/home/details',
        query: {
          id: id
        }
      })
    },
    // 点击切换喜欢
    switchLike(id) {
      console.log(id)
      const shopId = {
        shopId: id
      }
      postRestaurantStatus(shopId).then(res => {
        console.log(res)
        const { code, msg } = res.data
        if (code === 0) {
          Toast.success(msg)
        } else {
          Toast.fail(msg)
        }
      })
      // if (this.restaurantList[index].isShowLike) {
      //   this.restaurantList[index].isShowLike = false
      // } else {
      //   this.restaurantList[index].isShowLike = true
      // }
    },
    // 点击增加房间
    addRoom() {
      if (this.room <= 3) {
        this.room += 1
      } else {
        Toast.fail('不能再多了哦！')
      }
    },
    // 点击增加人数
    addQuantity() {
      if (this.quantity <= 30) {
        this.quantity += 1
      } else {
        Toast.fail('不能再多了，再多就真坐不下了！')
      }
    },
    // 选择日期回调
    formatDate(date) {
      //   return `${date.getMonth() + 1}/${date.getDate()}`
      return `${date.getDate()}`
    },
    onConfirm(date) {
      const [start, end] = date
      this.isShowDate = false
      //   this.startDate = `${this.formatDate(start)} - ${this.formatDate(end)}`
      this.startDate = this.formatDate(start)
      this.endtDate = this.formatDate(end)
      console.log(this.startDate, this.endtDate, this.isShowDate)
    },
    // 选择日期
    choiceDate() {
      if (this.isShowDate) {
        this.isShowDate = false
      } else {
        this.isShowDate = true
      }
    },
    // 返回上一页
    onClickLeft() {
      this.$router.back()
    }
  }
}
</script>

<style lang="scss" scoped>
.popular_destinations {
  //   width: 100%;
  height: 100%;
  overflow-y: auto;
  overflow-x: hidden;
  padding: 10px;
  // 选择
  .choice {
    width: 98%;
    padding: 10px 5px;
    border-radius: 10px;
    background-color: #f1f2f3;
    display: flex;
    justify-content: space-around;
    //  日期
    .choice_date {
      display: flex;
      //   图标
      .choice_Icon {
        margin-right: 5px;
      }
    }
  }
}
</style>
