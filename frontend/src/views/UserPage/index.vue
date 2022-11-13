<template>
  <div class="user_page">
    <!-- 用户信息 -->
    <div class="user_info">
      <div class="left">
        <van-image
          width="70"
          height="70"
          :src="
            'http://43.140.198.220:7777/upload/' + $store.state.userInfo.img
          "
        />
      </div>
      <div class="right">
        <!-- name -->
        <div class="user_name">
          <h3>{{ $store.state.userInfo.name }}</h3>
        </div>
        <!-- info -->
        <div class="user_info">{{ $store.state.userInfo.email }}</div>
      </div>
    </div>
    <!-- 功能列表 -->
    <div class="function_list">
      <!-- ****** -->
      <div class="function" @click="jumpManager">
        <div><van-icon name="manager" /></div>
        <div>Personal information</div>
        <div><van-icon name="arrow" /></div>
      </div>
      <!-- /****** */ -->
      <div class="function" @click="jumpBag">
        <div><van-icon name="bag" /></div>
        <div>Payment</div>
        <div><van-icon name="arrow" /></div>
      </div>
      <!-- /****** */ -->
      <div class="function" @click="jumpBell">
        <div><van-icon name="bell" /></div>
        <div>Notification</div>
        <div><van-icon name="arrow" /></div>
      </div>
      <!-- /****** */ -->
      <div class="function" @click="jumpBars">
        <div><van-icon name="bars" /></div>
        <div>L anguage</div>
        <div><van-icon name="arrow" /></div>
      </div>
      <!-- /****** */ -->
      <div class="function" @click="jumpQuestin">
        <div><van-icon name="question" /></div>
        <div>Get Help</div>
        <div><van-icon name="arrow" /></div>
      </div>
      <!-- /****** */ -->
      <div class="function" @click="jumpGuide">
        <div><van-icon name="guide-o" /></div>
        <div>Send us feedback</div>
        <div><van-icon name="arrow" /></div>
      </div>
      <!-- /****** */ -->
    </div>
    <!-- d底部 -->
    <div class="bottom">
      <van-button plain hairline type="primary" round @click="signOutLoogin">
        Sign Out
      </van-button>
    </div>
  </div>
</template>

<script>
import { Dialog, Toast } from 'vant'
export default {
  name: 'UserPage',
  data() {
    return {
      // 用户数据
      user: {
        img: require('@/assets/image/20220911180102.jpg'), // 头像
        name: 'Xiaoming', // 名字
        info: '1458299791@qq.com' // 信息
      }
    }
  },
  created() {
    this.init()
  },
  methods: {
    init() {
      if (!this.$store.state.userInfo.name) {
        Toast('请重新登录')
        localStorage.removeItem('token')
        this.$store.commit('USER_INFO', '')
        this.$router.push({ path: '/' })
      }
    },
    // 点击功能
    jumpManager() {
      Toast('功能完善中~')
    },
    // 点击功能
    jumpBag() {
      Toast('功能完善中~')
    },
    // 点击功能
    jumpBell() {
      Toast('功能完善中~')
    },
    // 点击功能
    jumpBars() {
      Toast('功能完善中~')
    },
    // 点击功能
    jumpQuestin() {
      Toast('功能完善中~')
    },
    // 点击功能
    jumpGuide() {
      Toast('功能完善中~')
    },
    // 退出登录
    signOutLoogin() {
      Dialog.confirm({
        message: '确定要退出登录吗？'
      })
        .then(() => {
          // 退出登录移除token     removeItem删除
          localStorage.removeItem('token')
          this.$store.commit('USER_INFO', '')
          this.$router.push({
            path: '/'
          })
        })
        .catch(() => {
          // on cancel
        })
    }
  }
}
</script>

<style lang="scss" scoped>
.user_page {
  padding: 10px;
  // height: 100%;
  padding-bottom: 100px;
  // 底部
  .bottom {
    margin-top: 100px;
    text-align: center;

    ::v-deep .van-button--primary {
      padding: 0 100px;
      color: red;
      border-color: red;
    }
  }
  // 功能列表
  .function_list {
    margin-top: 40px;
    .function {
      display: flex;
      justify-content: space-between;
      margin: 40px 0px;
    }
  }
  // 用户信息
  .user_info {
    display: flex;
    .left {
      ::v-deep .van-image__img {
        border-radius: 100%;
      }
    }
    .right {
      margin-left: 30px;
      .user_name {
        h3 {
          line-height: 5px;
        }
      }
      .user_info {
        color: #595c68;
        display: -webkit-box; //使用了flex，需要加
        overflow: hidden; //超出隐藏
        word-break: break-all; //纯英文、数字、中文
        text-overflow: ellipsis; //省略号
        -webkit-box-orient: vertical; //垂直
        -webkit-line-clamp: 1; //显示一行
        white-space: pre-line; //合并空白符序列，但是保留换行符。(如果要使用这个垂直和显示几行的代码也要写)
      }
    }
  }
}
</style>
